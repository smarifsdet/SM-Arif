package assignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class JavaTest2 {

	List<String> myArrayList = new ArrayList<String>();
	 
	
	@Before
	public void setup() {
		myArrayList.add("Chrome");
		myArrayList.add("FireFox");
		myArrayList.add("IE");
		myArrayList.add("Safari");
		myArrayList.add("Chrome");
	}

	/**
	 * Testing general Java String function knowledge
	 */

	@Test
	public void funWithJavaStrings() {		
		
		String companyName = "Dom & Tom Inc.";
		// Assert that the word 'Dom' is present in companyName string
		
//		Assert.assertEquals(companyName, companyName.contains("Dom"));
		
//		Assert.assertEquals(companyName, companyName.contains("Dom"));
		Assert.assertTrue(companyName.contains("Dom"));
	
		String name1 = "Netflix";
		String name2 = "NETFLIX";
		// Assert that name1 equals name2 regardless of case and leading/trailing spaces
		
		String string1=name1.trim();
		String string2=name2.trim();
//		Assert.assertTrue(string1.equalsIgnoreCase(string2));
//		Assert.assertTrue(string1.equalsIgnoreCase(string2));
		
		Assert.assertNotEquals(string1, string2);
		
		String intValue1 = "100";
		String intValue2 = "20";
		// Convert the above 2 strings to integer values and display the sum
		
		int num1=Integer.parseInt(intValue1);
		int num2=Integer.parseInt(intValue2);
		
		System.out.println("The sum is: "+(num1+num2));
		System.out.println("==============================================================================");
	}

	/**
	 * Testing knowledge of java Lists. The list 'myArrayList' has already been
	 * created and populated in @before method. Follow steps outlined below.
	 */

	@Test
	public void funWithArrayLists() {

		// Step 1 display the number of elements in myArrayList
		
		
		System.out.println("the number of elements in myArrayList is: " +myArrayList.size());
		System.out.println("==============================================================================");


		// Step 2 using enhanced for-loop loop thru myArrayList and display all values to console

		System.out.println("The values presnt in myArrayList are: ");
		for (String str : myArrayList)
		{
			System.out.println(str);
			
		}
		
		System.out.println("==============================================================================");
		// Step 3 Display the 3rd element in myArrayList
		
		System.out.println("the 3rd element in myArrayList is: "+myArrayList.get(2));
		System.out.println("==============================================================================");
		
		
		/**
		 * Step 4 Loop thru myArrayList to determine if any elements value = 'IE'
		 * if yes, display 'IE found @ index' and the index number in the  console then exit the loop	
		 */

		
		
		for(int i=0;i<myArrayList.size();i++)
			{
				String value=myArrayList.get(i);
				
				if (value=="IE")
				{
					System.out.println("IE found @ "+i+" index");
				}
				
				
			}
		
		System.out.println("==============================================================================");		
		
		/**
		 * Step 5 Loop thru myArrayList to determine if any list elements value = 'Opera'
		 * if not , display 'Opera not found'
		 */
		
		for(int i=0;i<myArrayList.size();i++)
		{
			String value=myArrayList.get(i);
			
			if (value.contentEquals("Opera"))
			{
				System.out.println("Opera found");
			}
			
			if (!value.contentEquals("Opera"))
			{
				System.out.println("Opera not found");
				
			}
			
			break;
			
		}
		
		System.out.println("==============================================================================");
	}	 

	/**
	 * Testing knowledge of java HashMaps Follow steps outlined below
	 */

	@Test
	public void funWithHashMaps() {
		
		HashMap<Integer, String> myHashMap = new HashMap<Integer, String>();
		
		/*
		 * Step 1 Load the following into ' myHashMap'
		 * 
		 * Key: = 1 Value: = "XP" 
		 * Key: = 2 Value: = "WIN7" 
		 * Key: = 3 Value: = "WIN8"
		 */
		
		myHashMap.put(1, "XP");
		myHashMap.put(2, "WIN7");
		myHashMap.put(3, "WIN8");
		
		// Step 2 Display to console the value associated with key 2
		
		System.out.println("The value associated with key 2 is: "+myHashMap.get(2));
		
		System.out.println("==============================================================================");
		// Step 3 Display the number of elements in myHashMap

		System.out.println("The number of elements in myHashMap is: "+myHashMap.size() );
		System.out.println("==============================================================================");
		// Step 4 Remove the last element in myHashMap and display the number of elements in myHashMap
		myHashMap.remove(3);
		
		System.out.println("The number of elements after removing the last element in myHashMap is: "+myHashMap.size());
		
		System.out.println("==============================================================================");
	}

	/**
	 * Testing knowledge of calling and processing a RESTFUL WebService call using Apache HttpClient 
	 * Follow the steps outlined below
	 * @throws IOException 
	 * @throws ClientProtocolException
	 */

	@Test
	public void funWithWebServices() throws ClientProtocolException, IOException {
	 
		String webServiceUrl = "http://api.geonames.org/cities?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo";
		
		// Step 1 Create instance of httpClient

		HttpClient httpClient = HttpClientBuilder.create().build();
       
		/*
   		 * Step 2 Create the Get Request, call it myGetRequest using the HttpGet
		 * class and webServiceUrl
 	   	 */ 	   
 		 
		 HttpGet myGetRequest = new HttpGet(webServiceUrl);
 		/*
		 * Step 3 Using the httpClient object created in step 1 execute
		 * myGetRequest and save the HttpResponse to myHttpResponse
 		 */

		 HttpResponse myHttpResponse = httpClient.execute(myGetRequest);
		// Step 4 Using myHttpResponse display the statuscode and ReasonPhrase to console

		 
		 int statuscode = myHttpResponse.getStatusLine().getStatusCode();
		 
		 System.out.println("The statuscode is: "+statuscode+" ReasonPhrase is: "+myHttpResponse.getStatusLine().getReasonPhrase());
		 System.out.println("==============================================================================");
 	    /*******EXTRA CREDIT ****** 
	  	* Process the myHttpResponse and display the JSON to the console
	  	* ...hint the response content is returned in myHttpResponse.getEntity().getContent()  
		* use BufferedReader
	  	*/ 
		 
		 HttpEntity entity = myHttpResponse.getEntity(); 

		 
		 
		 if (entity != null) {

			 String responseBody = EntityUtils.toString(entity);
		        System.out.println("the response content is :"+responseBody.toString());
		        
		        System.out.println("==============================================================================");
		 }
		 
		 
	}

	/**
	 * Testing knowledge of java HashSets Follow steps outlined below
	 * 
	 */
	@Test
	public void funWithHashSets() {

		// * Step 1 Create a HashSet of String objects called myHashSet
		Set<String> myHashSet = new HashSet<String>();

		/*
		 * Step 2 add the following String objecta to myHashSet "XP" "WIN7"
		 * "WIN8" "Safari" "XP"
		 */
		
		myHashSet.add("WIN8");
		myHashSet.add("Safari");
		myHashSet.add("XP");
		
		
		/* 
		 * Step 3 Display to console all the values of myHashSet ... hint use iterator
		 */	
        
		Iterator value = myHashSet.iterator(); 
		
		 System.out.println("all the values of myHashSet are: "); 
	        while (value.hasNext()) { 
	            System.out.println(value.next()); 
	        } 
	        
	        System.out.println("==============================================================================");
		// Step 4 Remove from myHashSet where String = 'XP"
	        
	        myHashSet.remove("XP");

		// Step 5 Display to console the number of elements in myHashSet
	
	        System.out.println("the number of elements in myHashSet are: "+myHashSet.size());
	        System.out.println("==============================================================================");
	        
	}	
}