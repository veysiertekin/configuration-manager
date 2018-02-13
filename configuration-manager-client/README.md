#### Configuration Manager Client

---


### 1. Kurulum

ℹ️ Sisteminizde `java 8` kurulu olduğundan emin olunuz, ve lütfen `maven` komutunun `java 1.8` ile çalıştığını doğrulayınız:

```bash
➜  ./mvnw -version

Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T10:58:13+03:00)
Maven home: /Users/<user>/.m2/wrapper/dists/apache-maven-3.5.2-bin/28qa8v9e2mq69covern8vmdkj0/apache-maven-3.5.2
Java version: 1.8.0_152, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_152.jdk/Contents/Home/jre
Default locale: en_TR, platform encoding: UTF-8
OS name: "mac os x", version: "10.13.3", arch: "x86_64", family: "mac"
```

ℹ️ Derlenme aşamasında testlerin çalışabilmesi `docker` yardımıyla embedded bir `mongodb` çalıştırılacağı için sisteminizde `docker`ın kurulu olduğundan emin olunuz.

```bash
➜  docker -v      
Docker version 17.09.0-ce, build afdb6d4
```


Uygulama jar'ının oluşturulması için aşağıdaki komutu çalıştırabilirsiniz:

```bash
➜  ./mvnw clean verify
```

---

### 2. Kütüphanenin Kullanımı

#### 2.1 Herhangi bir önbellek yapısı olmadan kullanımı

```java
ConfigurationReader reader = ConfigurationReaderFactory.createWithoutCache(
      applicationName,
      dbConnectionString
    );

String value = reader.getValue("key", String.class);
...

```


#### 2.1 Önbellek yapısı ile kullanımı

```java
ConfigurationReader reader = ConfigurationReaderFactory.createWithCaffeine(
      applicationName,
      dbConnectionString,
      refreshIntervalInMs
    );

String value = reader.getValue("key", String.class);
...

```


#### 2.1 Yeni bir önbellek yapısı eklenmesi

Cache yapısı baglantı arasına alınmış, dışarıdan bakıldığında sanki herhangi bir cache yapısı yokmuş gibi kurgulanmaktadır. Bunun için normal db client'larını tanımladığımız `ConfigurationClient` sınıfından oluşturulmuş yeni bir sınıf oluşturmamız gerekiyor.  Girdilerde gerçek veri bağlantısını sağlayan istemci yanında önbelleği ne kadar sıklıkla güncelleyeceğimize zaman aşımı bilgilerini almamız gerekiyor:

```java
...
public class CaffeineConfigurationClient implements ConfigurationClient {

  public CaffeineConfigurationClient(ConfigurationClient baseConfigurationClient, Integer refreshIntervalInMs) {
...

```

Yeni önbeleğimize özgü sınıfın cache yapısını uygulayıp, yenileme için bir zamanlayıcı kuruyoruz:

```java
cache = Caffeine.newBuilder()
      .build(this::load);

this.setTimer(cache, refreshIntervalInMs);
```

Zamanlayıcı kurgulamak için en kolay yollardan biri olan `Timer` sınıfı kullanabilirsiniz. Bunun için öncelikle `TimerTask` sınıfından türemiş yeni bir sınıf yaratmamız gerekiyor:

```java
public class CaffeineRefreshTask extends TimerTask {
  private static final Logger logger = LoggerFactory.getLogger(CaffeineRefreshTask.class);

  private final LoadingCache<String, ConfigurationDto> cache;

  public CaffeineRefreshTask(LoadingCache<String, ConfigurationDto> cache) {
    this.cache = cache;
  }

  @Override
  public void run() {
    try {
      this.cache.asMap().keySet().forEach(cache::refresh);
    } catch (Exception e) {
      logger.error("An error occur while refreshing", e);
    }
  }
}
```

Sınıfı yarattıktan sonra ise cache yapısını barındıran sınıfımızda timer kurulumunu gerçekleştiriyoruz:

```java
...
private void setTimer(LoadingCache<String, ConfigurationDto> cache, Integer refreshIntervalInMs) {
	Timer timer = new Timer(true);
	timer.scheduleAtFixedRate(new CaffeineRefreshTask(cache), 0, refreshIntervalInMs);
}
...
```




