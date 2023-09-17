package reader_day46;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Reader {

	public void propertyReader() throws Exception {

		String fileLocation = "/home/ramkumarra/Documents/SeleniumMain/src/main/java/reader_day46/Environment.properties";
		FileInputStream file = new FileInputStream(fileLocation);
		Properties pro = new Properties();
		pro.load(file);
		System.out.println(pro.getProperty("sitURL"));
		file.close();

	}

	public void jsonReader() throws Exception {

		String fileLocation = "/home/ramkumarra/Documents/SeleniumMain/src/main/java/reader_day46/simple.json";
		FileReader read = new FileReader(fileLocation);
		JSONParser par = new JSONParser();
		Object parObject = par.parse(read);
		JSONObject obj = (JSONObject) parObject;
		long id = (Long) obj.get("id");
		System.out.println(id);
		read.close();
	}

	public void jsonReaderArray() throws Exception {
		String FileLocation = "/home/ramkumarra/Documents/SeleniumMain/src/main/java/reader_day46/simple.json";
		FileReader file = new FileReader(FileLocation);
		JSONParser par = new JSONParser();
		Object obj = par.parse(file);
		JSONObject jsonObj = (JSONObject) obj;

		JSONArray jsonArray = (JSONArray) jsonObj.get("menuItem");
		System.out.println("Array Size is " + jsonArray.size());
		System.out.println("Array Elements are " + jsonArray);
		System.out.println("===============================");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonArrayMenu = (JSONObject) jsonArray.get(i);
			System.out.println("for " + (i + 1));
			String value = (String) jsonArrayMenu.get("value");
			System.out.println("Json Value of " + (i + 1) + " " + value);
			String OnClick = (String) jsonArrayMenu.get("onClick");
			System.out.println(OnClick);
		}
		file.close();
	}

	public void jsonReaderMap() throws Exception {

		String fileLocation = "/home/ramkumarra/Documents/SeleniumMain/src/main/java/reader_day46/simple.json";
		FileReader file = new FileReader(fileLocation);
		JSONParser par = new JSONParser();
		Object obj = par.parse(file);
		JSONObject jsonObj = (JSONObject) obj;

		Map<String, String> jsonMap = (Map) jsonObj.get("Address");

		Set<Entry<String, String>> entrySet = jsonMap.entrySet();

		for (Entry<String, String> e : entrySet) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		file.close();
	}

	public static void main(String[] args) throws Exception {

		Reader readerObj = new Reader();
		readerObj.propertyReader();
		readerObj.jsonReader();
		readerObj.jsonReaderArray();
		readerObj.jsonReaderMap();
	}

}
