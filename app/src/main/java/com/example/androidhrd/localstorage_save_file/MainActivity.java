package com.example.androidhrd.localstorage_save_file;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnWriteInternal,btnReadInternal,btnWriteCache,btnReadCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadInternal=findViewById(R.id.btnReadInternal);
        btnWriteInternal=findViewById(R.id.btnWriteInternal);
        btnReadCache=findViewById(R.id.btnReadCache);
        btnWriteCache=findViewById(R.id.btnWriteCache);

        btnWriteInternal.setOnClickListener(v->{
            writeToInternal();
        });

        btnReadInternal.setOnClickListener(v->{
            readInternalStorage();
        });

        btnWriteCache.setOnClickListener(v->{
            writeCache();
        });
        btnReadCache.setOnClickListener(v->{

        });
    }

    public void writeToInternal(){
        FileOutputStream fileOutputStream=null;
        String content="internal storage contents ";
        try{
            fileOutputStream=openFileOutput("test.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            Toast.makeText(this, "write successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readInternalStorage(){
        FileInputStream fis= null;
        try{
            fis=openFileInput("test.txt");
            int byteData=0;
            String data=null;
            while ( (byteData=fis.read() )!=-1 ){
                data=data+ (char) byteData;
            }
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeCache(){
        File file=null;
        try{
            file=File.createTempFile("cache1",null,getCacheDir());
            file.createNewFile();
            OutputStream ous=new FileOutputStream(file);
            ous.write("write cache".getBytes());
            Toast.makeText(this, "write cache successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
