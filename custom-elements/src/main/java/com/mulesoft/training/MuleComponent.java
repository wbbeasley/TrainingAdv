package com.mulesoft.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.api.annotations.param.InboundHeaders;
import org.mule.api.annotations.param.Payload;

public class MuleComponent {
	int count;

	public MuleComponent() {
		count = 1;
	}

	public Map<String, String> processMap(Map<String, String> inputVar) {
		// processMap implementation
		inputVar.put("executed by", "processMap method");
		return inputVar;
	}

	public Map<String, String> processArray(List<String> inputVar) {
		// processArray implementation
		inputVar.add("executed by processArray");
		Map<String, String> output = new HashMap<String, String>();
		output.put("message", inputVar.get(0));
		output.put("executed by", "processArray method");
		return output;
	}

	public Map<String, String> processString(String inputVar) {
		Map<String, String> output = new HashMap<String, String>();
		output.put("message", inputVar);
		output.put("executed by", "processString method");
		return output;
	}

	// This takes precendence over the others because reflection resolution is
	// after this
	public Map<String, String> processStringAll(@Payload Object inputVar,
			@InboundHeaders("http.method") String myHttpMethod) {
		Map<String, String> output = new HashMap<String, String>();
		output.put("message", inputVar.toString());
		output.put("executed by", "processAll method");
		output.put("http method used", myHttpMethod);
		output.put("count", String.valueOf(count));

		count++;
		return output;
	}
}
