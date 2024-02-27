 package com.projeto.intentsimplicitas.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.projeto.intentsimplicitas.interfaces.Action0;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

 public abstract class CustomAsyncTask extends AsyncTask<String, Void, Void> {

    private static final String TAG = CustomAsyncTask.class.getSimpleName();
    // entrada
    private Context context;
    private String jsonEnvio;
    private Integer timeoutConnect;
    private Integer timeoutRead;
    private Action0 customOnPostExecute;
    private String menssagemAguarde;

    // saida
    private String conteudoRetorno;
    private String retornoErro;
    private int responseCode;

    private ProgressDialog dialog;
    private boolean cancelled, avoidShowInterface, cancelledBytimeoutException;

    public CustomAsyncTask(Context context, String jsonEnvio) {
        this(context, jsonEnvio, 0, null, 0);
    }

    public CustomAsyncTask(Context context, String jsonEnvio, String messageWait) {
        this(context, jsonEnvio, 0, messageWait, 0);
    }

    public CustomAsyncTask(Context context, String jsonEnvio, int timeoutConnect, String mensagem, int timeoutRead) {
        this.context = context;
        this.jsonEnvio = jsonEnvio;
        this.timeoutConnect = timeoutConnect;
        this.timeoutRead = timeoutRead;
        this.menssagemAguarde = mensagem;
        dialog = new ProgressDialog(context);
    }

    protected void onPreExecute() {
        cancelled = false;
        cancelledBytimeoutException = false;
        customOnPostExecute = this::customOnPostExecute;

        boolean ok = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);//Pego a conectividade do contexto o qual o metodo foi chamado
        NetworkInfo netInfo = cm.getActiveNetworkInfo();//Crio o objeto netInfo que recebe as informacoes da NEtwork
        if ((netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable())) //Se o objeto for nulo ou nao tem conectividade retorna false
            ok = true;

        if (!ok) {
            setRetornoErro("Verifique a conexão com a internet!");
            cancelled = true;
            return;
        }

        if (!avoidShowInterface) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            if (menssagemAguarde == null || menssagemAguarde.isEmpty())
                dialog.setMessage("Aguarde, por favor..");
            else
                dialog.setMessage(menssagemAguarde);
            try {
                dialog.show();
            } catch (Exception e) {

            }
        }
    }

    protected Void doInBackground(String... urls) {
        if (cancelled)
            return null;

        BufferedReader reader = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        OutputStreamWriter wr = null;
        try {
            URL url = new URL(urls[0]);

            conn = (HttpURLConnection) url.openConnection();

            //timeout responsavel por controla o tempo de conexao com o servidor.
            if (timeoutConnect > 0) {
                conn.setConnectTimeout(timeoutConnect);
            }

            //timeout responsavel por controlar o tempo de resposta das requisicoes.
            if (timeoutRead != null && timeoutRead > 0) {
                conn.setReadTimeout(timeoutRead);
            }

            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(jsonEnvio);
            wr.flush();

            boolean error = false;

            final long beginTime = System.currentTimeMillis();

            /**
             * Se o codigo e de http 200 (OK)
             */
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();

            } else {
                error = true;
                is = conn.getErrorStream();
            }

            this.responseCode = conn.getResponseCode();

            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuilder sbResp = new StringBuilder();

            String line = reader.readLine();
            while (line != null) {
                sbResp.append(line);
                line = reader.readLine();
            }

            if (error) {
                retornoErro = sbResp.toString();
            } else {
                conteudoRetorno = sbResp.toString();
            }
        } catch (SocketTimeoutException Tex) {
            Log.e(TAG, "erro na execução", Tex);
            responseCode = 500;
            retornoErro = "Não foi possível concluir o processo, pois o timeout de execução foi atingido. Por favor tente novamente.";


//        Rotina estava deixando as notas comstatus 8 na Besni é necessario reavaliar.
//        catch (SocketTimeoutException Tex){
//            cancelledBytimeoutException = true;
//
//            ((Activity) context).runOnUiThread(() -> {
//                CustomAlertDialog.create(context)
//                	.setTitle("Atenção")
//                	.setMessage("Não foi possível concluir o processo. Deseja tentar novamente?")
//                    .setPositiveListener(() -> {
//                        CustomAsyncTask task = new CustomAsyncTask(context, jsonEnvio) {
//
//                            @Override
//                            public void customOnPostExecute() {
//                                conteudoRetorno = this.getConteudoRetorno();
//                                retornoErro = this.getRetornoErro();
//                                responseCode = this.getResponseCode();
//
//                                customOnPostExecute.call();
//                            }
//                        };
//                        task.execute(urls[0]);
//
//                    })
//                    .setNegativeListener(null).show();
//            });

        } catch (Exception ex) {
            Log.e(TAG, "erro na execução", ex);
            retornoErro = ex.getMessage();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (wr != null) {
                    wr.close();
                }
            } catch (Exception ex) {
            }
        }

        return null;
    }

    public abstract void customOnPostExecute();

    protected void onPostExecute(Void unused) {
        if (dialog != null && !avoidShowInterface) {
            try {
                dialog.dismiss();
            } catch (Exception e) {

            }

        }

        if (!cancelledBytimeoutException)
            customOnPostExecute();
    }

    public String getConteudoRetorno() {
        return conteudoRetorno;
    }

    public void setConteudoRetorno(String conteudoRetorno) {
        this.conteudoRetorno = conteudoRetorno;
    }

    public String getRetornoErro() {
        return retornoErro;
    }

    public void setRetornoErro(String retornoErro) {
        this.retornoErro = retornoErro;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setAvoidShowInterface(boolean avoidShowInterface) {
        this.avoidShowInterface = avoidShowInterface;
    }

    public String getMenssagemAguarde() {
        return menssagemAguarde;
    }

    public void setMenssagemAguarde(String menssagemAguarde) {
        this.menssagemAguarde = menssagemAguarde;
    }
}