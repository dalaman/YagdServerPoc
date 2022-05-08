public class Model {
    //static int id;
    //static void cursor;
    static StringBuffer text = new StringBuffer("");
    
    public static void updateModel(String newText){
        int length = text.length();
        text.replace(0, length, newText);       
    }

    public static String outputModel(){
        return new String(text);
    }
}
