package com.example.richardchen.myfile1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    //寫入按鈕與讀取按鈕
    private Button mSetBtn, mGetBtn;
    //輸入儲存文字的EditText
    private EditText mEdit;
    //顯示讀取文字的TextView
    private TextView mText;

    String absPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSetBtn = (Button) findViewById(R.id.setBtn);
        mEdit = (EditText) findViewById(R.id.edit);
        mGetBtn = (Button) findViewById(R.id.getBtn);
        mText = (TextView) findViewById(R.id.text);

        mSetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
 //                   File mSDFile = null;

//                    //檢查有沒有SD卡裝置
//                    if(Environment.getExternalStorageState().equals( Environment.MEDIA_REMOVED))
//                    {
//                        Toast.makeText(MainActivity.this , "沒有SD卡!!!" , Toast.LENGTH_SHORT ).show();
//                        return ;
//                    }
//                    else
//                   {
                        //取得SD卡儲存路徑
//                        mSDFile = Environment.getExternalStorageDirectory();
//                    }

//                  File mSDFile = new File ("/storage/emulated/0");
//                  File mSDFile = new File ("/storage/usbdisk6");
                    File mSDFile = new File ("/mnt/media_rw/usbdisk6");


                    String parent = mSDFile.getParent();
                    String Name   = mSDFile.getName();
                           absPath= mSDFile.getAbsolutePath();

                    //建立文件檔儲存路徑
//                  File mFile = new File(mSDFile.getParent() + "/" + mSDFile.getName() + "/MyAndroid");
                    File mFile = new File(absPath + "/MyAndroid");

                    //若沒有檔案儲存路徑時則建立此檔案路徑
                    if(!mFile.exists())
                    {
                        mFile.mkdirs();
                    }

                    //取得mEdit文字並儲存寫入至SD卡文件裡
                    FileWriter mFileWriter = new FileWriter( absPath + "/MyAndroid/Pubby.txt" );

                    mFileWriter.write(mEdit.getText().toString());
                    mFileWriter.close();
                    Toast.makeText(MainActivity.this, "File writing success!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "File writing error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGetBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    //取得SD卡儲存路徑
                    //File mSDFile = Environment.getExternalStorageDirectory();

                    //讀取文件檔路徑
                    FileReader mFileReader = new FileReader(absPath + "/MyAndroid/Pubby.txt");

                    BufferedReader mBufferedReader = new BufferedReader(mFileReader);
                    String mReadText = "";
                    String mTextLine = mBufferedReader.readLine();

                    //一行一行取出文字字串裝入String裡，直到沒有下一行文字停止跳出
                    while (mTextLine!=null)
                    {
                        mReadText += mTextLine+"\n";
                        mTextLine = mBufferedReader.readLine();
                    }
                    //文字放入mText裡，並清空mEdit
                    mText.setText(mReadText);
                    Toast.makeText(MainActivity.this, "已讀取文字", Toast.LENGTH_SHORT).show();
                    mEdit.setText("");
                }
                catch(Exception e)
                {
                }
            }
        });
    }
}
