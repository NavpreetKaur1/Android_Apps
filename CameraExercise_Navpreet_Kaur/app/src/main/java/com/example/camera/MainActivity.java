package com.example.camera;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {
    TextView name;
    private static final int TAKE_AVATAR_CAMERA_REQUEST = 1;
    SharedPreferences settings;
    public static final String TAG_NAME = "nameKey";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
        initAvatar();

        Button next = (Button) findViewById(R.id.Button_Submit);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Page2Activity.class);
                EditText userText = (EditText) findViewById(R.id.EditText_FirstName);
                String nameIntent = userText.getText().toString();
               // System.out.println("USERNAME IN MAIN ACTIVITY @#$@#$ " + name);

                 myIntent.putExtra("NAMEINTENT", nameIntent);
               // startActivity(intent);
                settings=getSharedPreferences(SETTINGS_PREFS,Context.MODE_PRIVATE);

                startActivityForResult(myIntent, 0);
            }

        });
    }

    private void initAvatar() {

        ImageView avatarImage = (ImageView) findViewById(R.id.ImageButton_Picture);
        if (settings.contains(SETTINGS_PREFS_AVATAR))
        {
            String avatarUri = settings.getString(SETTINGS_PREFS_AVATAR, "");

            if (avatarUri != "")
            {
                Uri imageUri = Uri.parse(avatarUri);
                avatarImage.setImageURI(imageUri);
            }
            else {
                avatarImage.setImageResource(R.drawable.avatar);
            }
        }
        else {
            avatarImage.setImageResource(R.drawable.avatar);
        }

        avatarImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent pictureIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(Intent.createChooser(
                        pictureIntent, "Take your photo"), TAKE_AVATAR_CAMERA_REQUEST);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case TAKE_AVATAR_CAMERA_REQUEST:
                if(resultCode == Activity.RESULT_CANCELED)
                {
                    //Avatar camera was cancelled
                }
                else if (resultCode == Activity.RESULT_OK)
                {
                    //Avatar cmera executed ok
                }

        }
        Bitmap cameraPic = (Bitmap) data.getExtras().get("data");
        if (cameraPic != null) {
            try
            {
                saveAvatar(cameraPic);
            }
            catch (Exception e)
            {

            }
        }
    }
    private void saveAvatar(Bitmap avatar) {
        String avatarFilename = "avatar.jpg";

        try {
            avatar.compress(Bitmap.CompressFormat.JPEG, 100, openFileOutput(avatarFilename, MODE_PRIVATE));
        }
        catch (Exception e) {

        }
        Uri avatarUri = Uri.fromFile(new File (
                this.getFilesDir(), avatarFilename));
        ImageButton avatarButton = (ImageButton) findViewById(R.id.ImageButton_Picture);
        avatarButton.setImageURI(null);
        avatarButton.setImageURI(avatarUri);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(SETTINGS_PREFS_AVATAR, avatarUri.getPath());
        editor.commit();
    }
}


