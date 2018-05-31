package com.att.test.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONArrayDocuments {

	public static void main(String[] args) throws JSONException {
		int totalSum = 0;
		int numberCount = 0;
		List<Integer> numberSumArray = new ArrayList<Integer>();
		String jsonFeed;
		if (args.length == 0) {
			System.out.println("pass in URL..");
			System.exit(0);
		}
		try {

			URL url = new URL(args[0]);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("error : " + conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			jsonFeed = br.readLine();

			JSONArray jsonarray = new JSONArray(jsonFeed);
			// 2. For each document display all of the keys of the JSON in
			// standard out
			System.out.println("-----------ALL KEYS --------------");
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject obj = jsonarray.getJSONObject(i);
				String[] keys = JSONObject.getNames(obj);
				for (String key : keys) {
					System.out.println(key);
				}
				// 3.There will be a json array of integers with the key of
				// "numbers", sum all of the integers and display
				// the sum on standard out, add that sum to a running total for
				// the program
				JSONArray arr = obj.getJSONArray("numbers");
				int numberSum = 0;
				for (int j = 0; j < arr.length(); j++) {
					numberSum += arr.getInt(j);
					numberCount++;
				}

				totalSum += numberSum;
				numberSumArray.add(numberSum);
			}
			System.out.println("-------------------------------");
			// sum of all integers and display on standard out
			System.out.println("sum of numbers\t" + numberSumArray.toString());
			// Adding the sum to running total and displaying on standard out.
			System.out.println("total sum of numbers\t" + totalSum);
			// 4. Display the total of the integers that were summed for the
			// execution
			System.out.println("total numbers\t" + numberCount);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}