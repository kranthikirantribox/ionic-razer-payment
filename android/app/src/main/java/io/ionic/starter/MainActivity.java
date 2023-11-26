package io.ionic.starter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.getcapacitor.Bridge;
import com.getcapacitor.BridgeActivity;
import com.razerpayment.plugin.RazerPaymentPlugin;

public class MainActivity extends BridgeActivity {
  String MerchantHost, OpType, Currency, Amount, OrderId, Channel, Tid, data_value;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    registerPlugin(RazerPaymentPlugin.class);

    // Check if the activity is created for the first time
    if (savedInstanceState == null) {
      handleIntentData(getIntent().getExtras());
    }
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    handleIntentData(intent.getExtras());
  }

  private void handleIntentData(Bundle extras) {
    if (extras != null) {
      MerchantHost = extras.getString("MerchantHost");
      OpType = extras.getString("OpType");
      Currency = extras.getString("Currency");
      Amount = extras.getString("Amount");
      OrderId = extras.getString("OrderId");
      Channel = extras.getString("Channel");
      Tid = extras.getString("Tid");
      data_value = extras.getString("Log");

      Log.e("111111111111111111", "Working");

      if (Amount != null) {
        Log.e("2222222222222", data_value);
        saveDataValueToSharedPreferences(MerchantHost,OpType, Currency,Amount,OrderId,Channel,Tid,data_value);

      }
    }
  }

  private void saveDataValueToSharedPreferences(String MerchantHost,String OpType, String Currency,String Amount,String OrderId,String Channel,String Tid,String dataValue) {
    // Use SharedPreferences to store the value
    SharedPreferences sharedPreferences = getSharedPreferences("READ_DATA", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("MerchantHost", MerchantHost);
    editor.putString("OpType", OpType);
    editor.putString("Currency", Currency);
    editor.putString("Amount", Amount);
    editor.putString("OrderId", OrderId);
    editor.putString("Channel", Channel);
    editor.putString("Tid", Tid);
    editor.putString("dataValue", dataValue);
    editor.apply();
  }
}
