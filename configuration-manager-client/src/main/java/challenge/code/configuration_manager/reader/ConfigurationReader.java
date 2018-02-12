package challenge.code.configuration_manager.reader;

public interface ConfigurationReader {
  <T> T getValue(String key, Class<T> klass);
}
