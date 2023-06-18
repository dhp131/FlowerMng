
package MyUses;

import java.io.*;
import java.util.*;


public class useFiles {

    private final static String SYSPATH = new File("").getAbsolutePath();

    private static String initPath(String path) {
        return SYSPATH + path;
    }
    
    //Đọc file
    public static ArrayList<String> readFromFile(String path) {
        String _path = initPath(path);
        File file = new File(_path);
        ArrayList<String> dta = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                dta.add(line.trim());
            }
            input.close() ;
        } catch (IOException e) {
//            System.out.println(e);
        }
        return dta;
    }

    //viết file
    public static boolean writeToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        File file = new File(_path);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (String line : dta) {
                output.write(line);
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //viết file theo chuỗi binary
    public static boolean writeBinaryStringToFile(String filePath, ArrayList<String> dta) {
        String _path = initPath(filePath);
        File file = new File(_path);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (String line : dta) {
                output.write(toBinaryString(line)) ;
                output.newLine();
            }
            output.close() ;
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    //đọc file theo kiểu binary
    public static ArrayList<String> readBinaryStringFromFile(String filePath){
        String _path = initPath(filePath) ;
        File file = new File(_path) ;
        ArrayList<String> data = new ArrayList<>() ;
        try{
            BufferedReader input = new BufferedReader(new FileReader(file)) ;
            String line ;
            while((line = input.readLine()) != null){
                data.add(toOriginString(line.trim())) ;
            }
            input.close() ;
        }catch(IOException e){}
        return data ;
    }

    //chuyển từ dạng binary thành kiểu String
    private static String toBinaryString(String str) {
        byte arr[] = str.getBytes();
        StringBuilder ret = new StringBuilder();
        for (byte b : arr) {
            String bStr = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            ret.append(bStr);
        }
        return ret.toString();
    }

    
    //từ dạng binary chuyển về file dạng origin để lọc được cái data mà mình mong muốn
    private static String toOriginString(String bStr) {
        ArrayList<Byte> arr = new ArrayList<>();
        for (int i = 0; i < bStr.length(); i += 8) {
            String chr = bStr.substring(i, i + 8);
            byte b = Byte.parseByte(chr, 2);
            arr.add(b);
        }
        byte[] ret = new byte[arr.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arr.get(i);
        }
        return new String(ret) ;
    }
}
