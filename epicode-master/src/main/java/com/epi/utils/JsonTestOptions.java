package com.epi.utils;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.PrintStream;

public class JsonTestOptions extends AbstractTestOptions {
    private final static String TEST_TAG = "###EPI_TESTS###\n";
    private PrintStream logstream;
    private JsonObjectBuilder jsonRoot;
    private JsonArrayBuilder jsonTests;

    public JsonTestOptions(PrintStream logstream) {
        this.logstream = logstream;
    }


    @Override
    public AbstractTestStream getStream() {
        return new JsonTestStream(jsonTests);
    }

    @Override
    public void startTests(int id, String description) {
        jsonRoot = Json.createObjectBuilder();
        jsonTests = Json.createArrayBuilder();

        jsonRoot.add("problem_id", "" + id);
        jsonRoot.add("problem_name", description);
    }

    @Override
    public void endTests() {
        jsonRoot.add("tests", jsonTests);
        logstream.print(TEST_TAG);
        logstream.print(jsonRoot.build().toString());
    }
}
