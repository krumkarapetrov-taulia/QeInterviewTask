package utils

class PropertyReader {


  static Properties properties
  static final String propertyFilePath = "./src/test/groovy/test.properties"

  PropertyReader() {
    BufferedReader reader
    try {
      reader = new BufferedReader(new FileReader(propertyFilePath))
      properties = new Properties()
      try {
        properties.load(reader)
        reader.close()
      } catch (IOException e) {
        e.printStackTrace()
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace()
      throw new RuntimeException("Test.properties not found at " + propertyFilePath)
    }
  }

  String getSystemPropertyByName(String propertyName) {
    String property = System.getProperty(propertyName)
    if (property != null) return property
    else throw new RuntimeException("${propertyName} not specified in the test.properties file.")
  }

  String getPropertyByName(String propertyName) {
    String property = properties.getProperty(propertyName)
    if (property != null) return property
    else throw new RuntimeException("${propertyName} not specified in the test.properties file.")
  }

  String getBrowser() {
    getPropertyByName("browser")
  }


}