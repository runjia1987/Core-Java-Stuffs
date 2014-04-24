package org.jackJew.io.performanceTest;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJsCall {

	/**
	 * @param args
	 * @throws ScriptException
	 */
	public static void main(String[] args) throws ScriptException {
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("javascript");
		
		String js = "(function addFn(){return (1 + 2 + 3.2);})();";
		System.out.println(engine.eval(js));
		
	}

}
