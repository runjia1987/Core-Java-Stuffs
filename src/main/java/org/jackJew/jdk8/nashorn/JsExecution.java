package org.jackJew.jdk8.nashorn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import jdk.nashorn.api.scripting.NashornScriptEngine;

/**
 * js execution in Java engine
 * @author Jack
 *
 */
public class JsExecution {

	public static void main(String[] args) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		NashornScriptEngine scriptEngine = (NashornScriptEngine)scriptEngineManager.getEngineByName("Nashorn");
		
		try(InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("script.js");
			BufferedReader br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
		    ) {
			String line;
			StringBuilder script = new StringBuilder();
			while((line = br.readLine()) != null) {
				script.append(line);
			}			
			Object result = scriptEngine.eval(script.toString(), new SimpleScriptContext());
			System.out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
