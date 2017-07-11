package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	public Properties get(){
		 Properties prop=new Properties();
	        try {
//	          FileInputStream is=new FileInputStream("config.properties");
	            InputStream is=this.getClass().getResourceAsStream("/name.properties");
	            prop.load(is);
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return prop;
	}
	
	/**
	 * 
	 * @Description: TODO
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author houhuayu
	 * @date 2016-8-18
	 */
	public static List<String> t2(){
		List<String> list = new ArrayList<String>();
		Test t = new Test();
		String jsonStr = t.get().toString();
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONObject json2= jsonObj.getJSONObject("name");
		JSONArray ja = json2.getJSONArray("portal1");
		JSONArray ja2 = json2.getJSONArray("portal2");
		for(int i=0;i<ja.size();i++){
			String p = ja.getJSONObject(i).getString("name");
			list.add(p);
		}
		for(int i=0;i<ja2.size();i++){
			String p = ja2.getJSONObject(i).getString("name");
			list.add(p);
		}
	return list;
		
	}
	
	public void t7(){
		File f = null;
	      File f1 = null;
	      boolean bool = false;
		 try{      
	         // create new File objects
			 f = new File("D:/美国");
	         f1 = new File("D:/中国");
	         
	         // rename file
	         bool = f.renameTo(f1);
	         
	         // print
	         System.out.print("File renamed? "+bool);
	         
	      }catch(Exception e){
	         // if any error occurs
	         e.printStackTrace();
	      }
	}

	/**
	 * @Description: TODO
	 * @param @param args   
	 * @return void  
	 * @throws IOException 
	 * @throws
	 * @author houhuayu
	 * @date 2016-8-18
	 */
	public static void main(String[] args) throws IOException {
		Test t = new Test();
//		t.t2();
		t.t7();
////		t.saveProperty();
//		System.out.println(t.get().toString());
//		String json = t.get().toString();
//		JSONObject obj = JSONObject.fromObject(t.get().toString());
//		System.out.println(obj);
//		
//		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(obj);  
//		String json_c = jsonArray.getString(0);  
//		JSONObject jsonObject = JSONObject.fromObject(json_c);  
//		for (Iterator iter = jsonObject.keys(); iter.hasNext();) { //先遍历整个 people 对象  
//		    String key = (String)iter.next();  
//		    System.out.println(key + "#" + jsonObject.getString(key));  
//		}
//		

	}

}
