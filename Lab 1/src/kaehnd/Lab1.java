/*
 * Course: CS 1021
 * Term: Winter 2018
 * Assignment: Lab 1
 * Name: Daniel Kaehn
 * Date: 11/29/18
 */

package kaehnd;

import edu.msoe.taylor.audio.WavFile;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Driver for WavFile Class which reverses an existing WAV or generates a mono or stereo
 * one-second WAV file at user specified frequency or frequencies
 */

public class Lab1 {

    private static final long WAV_SAMPLE_RATE = 22050; //Sample rate of a WAV file
    private static final long FRAMES = WAV_SAMPLE_RATE * 1; //WAV_SAMPLE_RATE * time(s)
    private static final int WAV_VALID_BITS = 16; //Bit depth of a WAV file
    private static final int MONO_CHANNELS = 1; //Channel number  for mono file
    private static final int STEREO_CHANNELS = 2; //Channel number for stereo file

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int action = 0;
        System.out.println("\nWelcome to WAV Workshop!\n");

        do{
            action = getInput(in);
            switch (action) {
                case 0:
                    break;
                case 1:
                    reverseFile(in);
                    break;
                case 2:
                    generateMono(in);
                    break;
                case 3:
                    generateStereo(in);
            }
            System.out.println();
        } while(action != 0);
        System.out.println("Thank you for using WAV Workshop!");
    }

    //Reverses user defined existing WAV and saves it in project directory
    private static void reverseFile(Scanner in){
        System.out.print("Enter a filename to reverse: ");
        String fileName = in.nextLine();
        WavFile fileToReverse = new WavFile(fileName + ".wav");

        //Retrieve dat from specified file
        int numChannels = fileToReverse.getNumChannels();
        long numFrames = fileToReverse.getNumFrames();
        int validBits = fileToReverse.getValidBits();
        long sampleRate = fileToReverse.getSampleRate();
        ArrayList<Double> samples = fileToReverse.getSamples();

        fileName += "Rev.wav";
        fileToReverse.close();

        //Reverse samples
        ArrayList<Double> revSamples = new ArrayList<>();
        for(int i = samples.size() - 1; i >= 0; i--){
            revSamples.add(samples.get(i));
        }

        //Create reversed file
        WavFile reversed = new WavFile(fileName, numChannels, numFrames, validBits, sampleRate);
        reversed.setSamples(revSamples);
        reversed.close();

        System.out.println("\nSuccess!\n");
    }


    //Generates a mono one-second WAV file with user specified name of a tone
    // at a user specified frequency
    private static void generateMono(Scanner in){
        System.out.print("Enter a filename for your byte: ");
        String fileName = in.nextLine() + ".wav";
        System.out.print("Enter a frequency: ");
        long frequency = in.nextLong();
        in.nextLine();
        ArrayList<Double> samples = new ArrayList<>();

        //Generate samples for mono channel
        for (int i = 0; i < FRAMES; i++){
            samples.add(Math.sin(2 * Math.PI * frequency * i / WAV_SAMPLE_RATE));
        }

        //Create file
        WavFile generatedTone = new WavFile(
                fileName, MONO_CHANNELS, FRAMES, WAV_VALID_BITS, WAV_SAMPLE_RATE);
        generatedTone.setSamples(samples);
        generatedTone.close();

        System.out.println("\nSuccess!\n");
    }

    //Generates a stereo one-second WAV file with user specified name of a tone
    // at a user specified frequencies
    private static void generateStereo(Scanner in){
        System.out.print("Enter a filename for your byte: ");
        String fileName = in.nextLine();
        fileName += ".wav";
        System.out.print("Enter a frequency: ");
        long frequencyOne = in.nextLong();
        System.out.print("Enter another frequency: ");
        long frequencyTwo = in.nextLong();
        in.nextLine();

        //Generate samples for left and right channels
        ArrayList<Double> samples = new ArrayList<>();
        for (int i = 0; i < FRAMES; i += 2){
            samples.add(i, Math.sin(2 * Math.PI * frequencyOne * i / WAV_SAMPLE_RATE));
            samples.add(i + 1, Math.sin(2 * Math.PI * frequencyTwo * i / WAV_SAMPLE_RATE));
        }

        //Create file
        WavFile generatedTone = new WavFile(
                fileName, STEREO_CHANNELS, FRAMES, WAV_VALID_BITS, WAV_SAMPLE_RATE);
        generatedTone.setSamples(samples);
        generatedTone.close();

        System.out.println("\nSuccess!\n");
    }

    //Displays program options, prompts user inputs, and validates input
    //Returns int value specifying desired action
    private static int getInput(Scanner in){
        boolean valid = false;
        String input = "";
        while (!valid) {
            System.out.println("Enter 1 to create reversed WAV file from existing WAV file\n" + "" +
                    "Enter 2 to create a one-second WAV file from a specified frequency\n" +
                    "Enter 3 to create a one-second stereo WAV file from two specified " +
                    "frequencies\nEnter 0 to exit\n");
            System.out.print("What would you like to do? ");
            input = in.nextLine();
            if (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3")) {
                valid = true;
            } else{
                System.out.println("\nThat input of \"" + input + "\"  wasn't valid\n");
            }
        }
        return Integer.parseInt(input);
    }
}