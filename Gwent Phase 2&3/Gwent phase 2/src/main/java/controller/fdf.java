//package controller;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonWriter;
//import model.Deck;
//
//import java.io.IOException;
//
//public class MyClassAdapter extends TypeAdapter<Deck> {
//    @Override
//    public void write(JsonWriter out, Deck myClass) throws IOException {
//        out.beginObject();
//        out.name("field1").value(myClass.getField1());
//        out.name("field2").value(myClass.getField2());
//        // Add other fields as needed
//        out.endObject();
//    }
//
//    @Override
//    public Deck read(JsonReader in) throws IOException {
//        Deck deck = new Deck();
//        in.beginObject();
//        while (in.hasNext()) {
//            switch (in.nextName()) {
//                case "field1":
//                    deck.setField1(in.nextString());
//                    break;
//                case "field2":
//                    deck.setField2(in.nextString());
//                    break;
//                // Add other fields as needed
//            }
//        }
//        in.endObject();
//        return deck;
//    }
//}
