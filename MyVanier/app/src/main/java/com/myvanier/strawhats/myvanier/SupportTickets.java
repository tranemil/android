package com.myvanier.strawhats.myvanier;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SupportTickets extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SupportTickets";
    private Button submit;
    Spinner sendMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_tickets);
        submit = (Button) findViewById(R.id.btn_submit_ticket);
        submit.setOnClickListener(this);
        sendMethod = (Spinner) findViewById(R.id.ticketSendMethod);
    }

    /**
     * Gets the text in the details box.
     * @return
     */
    public String getBody() {
        EditText body = (EditText) findViewById(R.id.newDate);
        String bodyTxt = body.getText().toString();
        return bodyTxt;
    }

    /**
     * Gets the selected item from the issues drop down.
     * @return Returns the selected issue
     */
    public String getIssue() {
        Spinner issues = (Spinner) findViewById(R.id.issue_drop);
        String issueSelected = issues.getSelectedItem().toString();
        //Toast.makeText(this,issueSelected,Toast.LENGTH_LONG).show();
        return issueSelected;
    }

    @Override
    public void onClick(View v) {
        String sendMethodTxt = sendMethod.getSelectedItem().toString();
        switch(v.getId())
        {
            case (R.id.btn_submit_ticket):
                // When email is selected as the send method
                Log.i("SUPPORTTEST",sendMethodTxt.equals("Email")+"");
                if(sendMethodTxt.equals("Email")) {
                    Intent email = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","werethestrawhats@gmail.com",null));
                    String[] addresses = {"werethestrawhats@gmail.com"};
//                    email.setDataAndType(Uri.parse("mailto:"),"text/plain");
                    email.putExtra(Intent.EXTRA_EMAIL,addresses);
                    email.putExtra(Intent.EXTRA_SUBJECT, getIssue());
                    email.putExtra(Intent.EXTRA_TEXT,getBody());
                    //checks for application that can send an email
                    if (email.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(email, "Send Email"));
                        finish();
                        Toast.makeText(this,"Email sent successfully",Toast.LENGTH_LONG).show();
                        Log.i(TAG,"Email sent, content= " + getIssue() + " " + getBody());
                    }
                    //if no application is found
                    else {
                        Toast.makeText(this,"Email sending failed, no app was found that supports email sending.",Toast.LENGTH_LONG).show();
                    }
                }
                // When SMS is selected as the send method
                else {
                    //TODO: Change number?
                    Uri smsUri = Uri.parse("smsto:4389928129");
                    Intent sms = new Intent(Intent.ACTION_SENDTO,smsUri);
                    sms.putExtra("sms_body",getIssue() + " | " + getBody());
                    // Tries to send an SMS with another application
                    try {
                        startActivity(sms);
                        finish();
                        Toast.makeText(this,"SMS sent successfully",Toast.LENGTH_LONG).show();
                        Log.i(TAG,"SMS sent, content= " + getIssue() + " " + getBody());
                    }
                    // If no application can be found
                    catch (android.content.ActivityNotFoundException e) {
                        Toast.makeText(this,"SMS sending failed",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
