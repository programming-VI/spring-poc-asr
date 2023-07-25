package com.poc.asr.web.app.model;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
  public static void main(String[] args) throws IOException {
    Configuration configuration = new Configuration();

    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

    configuration.setSampleRate(48000);

    StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
    InputStream stream = new FileInputStream("/home/santihs/SANTIAGO/1. Universidad Salesiana/PROGRAMMING VI/CODE/spring-poc-asr/src/main/java/com/poc/asr/web/app/model/test.wav");

    recognizer.startRecognition(stream);

    SpeechResult result;

    while ((result = recognizer.getResult()) != null) {
      System.out.format("Hypothesis: %s\n", result.getHypothesis());
    }
    recognizer.stopRecognition();
  }
}
