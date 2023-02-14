package com.lifesolutions.bd.ImageUpload;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UploadImageToServer {

    private static String downloadUrl = "none";

    public static String uploadImage(Context context, Bitmap image, String fileName, String filePath) {
        RequestBody folderPathPart = RequestBody.create(okhttp3.MultipartBody.FORM, filePath);

        File imageFile = null;
        try {
            imageFile = getImageFile(image,context,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestBody imageFilePart = RequestBody.create(
                MediaType.parse("image/jpeg"),
                imageFile
        );

        MultipartBody.Part file = MultipartBody.Part.createFormData("imageFile", fileName, imageFilePart);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.1.108:8201/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ImageServerClient client = retrofit.create(ImageServerClient.class);

        Call<ImageUpload> call = client.uploadImage(folderPathPart, file);

        call.enqueue(new Callback<ImageUpload>() {
            @Override
            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                if (response.isSuccessful()) {
                    response.body();
                    downloadUrl = response.body().getDownloadUrlRes();
                }
            }

            @Override
            public void onFailure(Call<ImageUpload> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return downloadUrl;
    }

    private static File getImageFile(Bitmap image, Context context, String filename) throws IOException {
        File file = new File(context.getCacheDir(), filename);
        file.createNewFile();

//Convert bitmap to byte array
        Bitmap bitmap = image;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
