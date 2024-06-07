package com.capacitapp.AccountManagerHelper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.capacitapp.MainActivity;

import java.io.IOException;

public class AccountManagerHelper {

    private AccountManager accountManager;

    public AccountManagerHelper(Context context) {
        accountManager = AccountManager.get(context);
    }

    public void createAccount(String useremail, String password) {
        Account[] accounts = accountManager.getAccountsByType("account_type");
        if (accounts.length == 0) {
            Account account = new Account(useremail, "account_type");
            try {
                boolean accountCreated = accountManager.addAccountExplicitly(account, password, null);
                if (!accountCreated) {
                    throw new SecurityException("Failed to create account");
                }
            } catch (SecurityException e) {
                e.printStackTrace();

            }
        }
    }


    public void getAuthToken(final Context context, final AccountManagerCallback<Bundle> callback) {
        Account[] accounts = accountManager.getAccountsByType("account_type");
        if (accounts.length > 0) {
            Account account = accounts[0];
            accountManager.getAuthToken(account, "auth_token_type", null, false, callback, null);
        } else {

            callback.run(null);
        }
    }

    public void login(final Context context, String username, String password) {

        createAccount(username, password);


        getAuthToken(context, new AccountManagerCallback<Bundle>() {
            @Override
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    Bundle result = future.getResult();
                    String token = result.getString(AccountManager.KEY_AUTHTOKEN);
                    if (token != null) {

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else {

                        Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                    }
                } catch (OperationCanceledException | AuthenticatorException | IOException e) {
                    e.printStackTrace();

                    Toast.makeText(context, "Error al obtener el token de autenticación", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




