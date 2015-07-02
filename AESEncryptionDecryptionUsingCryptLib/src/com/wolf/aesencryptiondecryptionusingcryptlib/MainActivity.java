package com.wolf.aesencryptiondecryptionusingcryptlib;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private final String TAG = MainActivity.this.getClass().getName();

	String mIV, mKey;

	CryptLib mCryptLib;

	String mPlainText = "This is the String to be Encrypted and Decrypted !!!";

	String mEncryptedString, mDecryptedString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			// Setting the main IV (Initialization Vector)
			mIV = "InitializationVector";

			// Encrypting the IV to generate a key
			// throws NoSuchPaddingException
			mKey = CryptLib.SHA256(mIV, 32);

			// Initializing cryptLib
			mCryptLib = new CryptLib();

			// Encrypting the mPlainText
			// Throws BadPaddingException,IllegalBlockSizeException,
			// InvalidAlgorithmParameterException, InvalidKeyException
			mEncryptedString = mCryptLib.encrypt(mPlainText, mKey, mIV);

			Log.e(TAG, "ENCRYPTED STRING: " + mEncryptedString);

			// Decrypting the encrypted string
			mDecryptedString = mCryptLib.decrypt(mEncryptedString, mKey, mIV);

			Log.e(TAG, "DECRYPTED STRING: " + mDecryptedString);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
