package com.example.ClassificaIp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edIp, edMascara, edClasseDeIp, edTipoDeIp, edTotalHost , edTotalRedes;
    private Button bt1, bt2;
    private String Fatiador[] = {"","","",""};
    private String  marcadorIp,marcadorMascara;
    private int contaBit[]={0,128,64,32,16,8,4,2,1,128,64,32,16,8,4,2,1,128,64,32,16,8,4,2,1};
    private String retornoMascaraAHosts[] = {"16777216","8388608","4194304","2097152","1048576","524288","262144","131072","65536","32768","16384","8192","4096","2048","1024","512","256","128","64","32","16","8","4","2","1"};
    private String retornoMascaraARedes[] = {"1","2","4","8","16","32","64","128","256","512","1024","2048","4096","8192","16384","32768","65536","131072","262144","524288","1048576","2097152","4194304","8388608","16777216"};
    private String retornoMascaraBHosts[] = {"65536", "32768", "16384", "8192", "4096", "2048", "1024", "512", "256", "128", "64", "32", "16", "8", "4", "2", "1"};
    private String retornoMascaraBRedes[] = {"256", "512", "1024", "2048","4096", "8192","16384", "32768", "65536", "131072", "262144", "524288", "1048576", "2097152", "4194304", "8388608", "16777216"};
    private String retornoMascaraCHosts[] = {"256", "128", "64", "32", "16", "8", "4", "2", "1"};
    private String retornoMascaraCRedes[] = {"65536", "131072", "262144", "524288", "1048576","2097152", "4194304", "8388608", "16777216"};
    private String mascaraB[] = {};
    private String mascaraC[] = {};

    private int octagIp[] = {0,0,0,0};
    private int mascaracheck[] = {0,0,0,0};
    int contador;
    boolean checkMascara = false;
    private  String mostraRedes, mostraHost, comparaMascara;
    private int conta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edIp = (EditText) findViewById(R.id.editText1);
        edMascara = (EditText) findViewById(R.id.editText2);
        edClasseDeIp = (EditText) findViewById(R.id.editText3);
        edTipoDeIp = (EditText) findViewById(R.id.editText4);
        edTotalHost = (EditText) findViewById(R.id.editText5);
        edTotalRedes = (EditText) findViewById(R.id.editText6);
        bt1 = (Button) findViewById(R.id.button1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcadorIp = edIp.getText().toString();
                marcadorMascara = edMascara.getText().toString();
                Fatiador = marcadorIp.split("\\.");

                contador = 0;
                for (String s : Fatiador) {
                    octagIp[contador] = Integer.parseInt(Fatiador[contador].toString());
                    contador++;
                }
                if (ValidacaoIp(octagIp)) {
                    edClasseDeIp.setText(Classe_Ip(octagIp[0]));
                    edTipoDeIp.setText(Tipo_Ip(octagIp[0], octagIp[1]));
                    edTotalRedes.setText(ToRedes());
                    edTotalHost.setText(ToHost());
                }
                else {
                    DisplayToast("Dados incorretos! Preencha com dados válidos");
                    Zera_Dados();
                }
            }
        });

        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zera_Dados();
            }
        });
    }// fim do OnCreate
    public String ToRedes() {
        mostraRedes = "";
        mostraHost = "";
        comparaMascara = edClasseDeIp.getText().toString();
        /*variaveis locais */
        String comparaIpA = "Classe A";
        String comparaIpB = "Classe B";
        String comparaIpC = "Classe C";
        String comparaIpD = "Classe D";
        String comparaIpE = "Classe E";
        String filtro [] = marcadorMascara.split("\\.");

        int  classeA = Integer.parseInt(filtro[0]);
        int  classeB = Integer.parseInt(filtro[1]);
        int  classeC = Integer.parseInt(filtro[2]);
        int  classeD = Integer.parseInt(filtro[3]);
        mascaracheck[0] = classeA;
        mascaracheck[1] = classeB;
        mascaracheck[2] = classeC;
        mascaracheck[3] = classeD;

        checkMascara = calcularMascaraPadrao(mascaracheck);
        /********************************** if da classe A************************************/

        if(comparaIpA.equals(comparaMascara)){
            if(classeA == 255 &&classeB <= 255 && classeC == 0 && classeD == 0) {
                for (int i = 0; i <= contaBit[7]; i++) {
                    conta += contaBit[i];
                    System.out.println("teste mas Mascara_A255.....  if(classeA == 255 classeB <= 255 && classeC == 0 && classeD == 0*********************"+ conta);
                    System.out.println("teste variável classeC *********************"+ classeC);
                    if (conta == classeB) {
                        mostraHost = retornoMascaraAHosts[i];
                        return mostraRedes = retornoMascaraARedes[i];
                    }
                    else if (classeB == 128) {
                        mostraHost = retornoMascaraAHosts[i+1];
                        return mostraRedes = retornoMascaraARedes[i+1];
                    }
                    else if(classeB == 196) {
                        mostraHost = retornoMascaraAHosts[i+2];
                        return mostraRedes = retornoMascaraARedes[i+2];
                    }
                    else if(classeB == 224) {
                        mostraHost = retornoMascaraAHosts[i+3];
                        return mostraRedes = retornoMascaraARedes[i+3];
                    }
                    else if(classeB == 240) {
                        mostraHost = retornoMascaraAHosts[i+4];
                        return mostraRedes = retornoMascaraARedes[i+4];
                    }
                    else if(classeB == 248) {
                        mostraHost = retornoMascaraAHosts[i+5];
                        return mostraRedes = retornoMascaraARedes[i+5];
                    }
                    else if(classeB == 252) {
                        mostraHost = retornoMascaraAHosts[i+6];
                        return mostraRedes = retornoMascaraARedes[i+6];
                    }
                    else if(classeB == 254) {
                        mostraHost = retornoMascaraAHosts[i+7];
                        return mostraRedes = retornoMascaraARedes[i+7];
                    }
                    else if(classeB == 255) {
                        mostraHost = "65536";
                        return mostraRedes = "256";
                    }

                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }
                }
            }
            else if(classeA == 255 &&classeB == 255 && classeC <=255&& classeD == 0){
                System.out.println("teste else if classeCC *********************"+ classeC);
                for (int i = 0; i <= contaBit[7];i ++){
                    // conta += contaBit[i];
                    System.out.println("teste mas Mascara_A  if(classeC <=255 && classeD == 0*********************"+ conta);
                    if (classeC == 128) {
                        System.out.println("teste dentro 128 *********************"+ classeC);
                        mostraHost = retornoMascaraAHosts[i+9];
                        return mostraRedes = retornoMascaraARedes[i+9];
                    }
                    else if(classeC == 196) {
                        mostraHost = retornoMascaraAHosts[i+10];
                        return mostraRedes = retornoMascaraARedes[i+10];
                    }
                    else if(classeC == 224) {
                        mostraHost = retornoMascaraAHosts[i+11];
                        return mostraRedes = retornoMascaraARedes[i+11];
                    }
                    else if(classeC == 240) {
                        mostraHost = retornoMascaraAHosts[i+12];
                        return mostraRedes = retornoMascaraARedes[i+12];
                    }
                    else if(classeC == 248) {
                        mostraHost = retornoMascaraAHosts[i+13];
                        return mostraRedes = retornoMascaraARedes[i+13];
                    }
                    else if(classeC == 252) {
                        mostraHost = retornoMascaraAHosts[i+14];
                        return mostraRedes = retornoMascaraARedes[i+14];
                    }
                    else if(classeC == 254) {
                        mostraHost = retornoMascaraAHosts[i+15];
                        return mostraRedes = retornoMascaraARedes[i+15];
                    }
                    else if(classeC == 255) {
                        mostraHost = "256";
                        return mostraRedes = "65536";
                    }
                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }
                }
            }
            else if( classeA == 255 && classeB == 255 && classeC == 255 && classeD < 255){
                System.out.println("teste else if classeCC *********************"+ classeC);
                for (int i = 0; i <= contaBit[7];i ++){
                    // conta += contaBit[i];
                    System.out.println("teste mas Mascara_A  if(classeC <=255 && classeD == 0*********************"+ conta);
                    if (classeD == 128) {
                        mostraHost = retornoMascaraAHosts[i+17];
                        return mostraRedes = retornoMascaraARedes[i+17];
                    }
                    else if(classeD  == 196) {
                        mostraHost = retornoMascaraAHosts[i+18];
                        return mostraRedes = retornoMascaraARedes[i+18];
                    }
                    else if(classeD  == 224) {
                        mostraHost = retornoMascaraAHosts[i+19];
                        return mostraRedes = retornoMascaraARedes[i+19];
                    }
                    else if(classeD  == 240) {
                        mostraHost = retornoMascaraAHosts[i+20];
                        return mostraRedes = retornoMascaraARedes[i+20];
                    }
                    else if(classeD  == 248) {
                        mostraHost = retornoMascaraAHosts[i+21];
                        return mostraRedes = retornoMascaraARedes[i+21];
                    }
                    else if(classeD  == 252) {
                        mostraHost = retornoMascaraAHosts[i+22];
                        return mostraRedes = retornoMascaraARedes[i+22];
                    }
                    else if(classeD  == 254) {
                        mostraHost = retornoMascaraAHosts[i+23];
                        return mostraRedes = retornoMascaraARedes[i+23];
                    }
                    else if(classeD  == 255) {
                        mostraHost = "1";
                        return mostraRedes = "16777216";
                    }
                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }

                }
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle(" Dados inseridos errados ");
            alerta.setMessage("Valores introduzidos no campo Mascara Incorretos");
            alerta.setNegativeButton("Ok",null).show();
            Zera_Dados();
            return mostraRedes = "";

        }//fim else geral comparaIpA
        /********************************** if da classe B************************************/
        else if (comparaIpB.equals(comparaMascara)) {

            if(classeA == 255 &&classeB == 255 && classeC <255&& classeD == 0){
                System.out.println("teste if MASCARA_B *********************"+ classeC);
                for (int i = 0; i <= contaBit[7];i ++){
                    // conta += contaBit[i];
                    if  (classeB == 255 && classeC ==0 && classeD == 0) {
                        System.out.println("teste dentro 128 *********************"+ classeC);
                        mostraHost = retornoMascaraBHosts[i];
                        return mostraRedes = retornoMascaraBRedes[i];
                    }
                    else if  (classeC == 128) {
                        System.out.println("teste dentro 128 *********************"+ classeC);
                        mostraHost = retornoMascaraBHosts[i+1];
                        return mostraRedes = retornoMascaraBRedes[i+1];
                    }
                    else if(classeC == 192) {
                        mostraHost = retornoMascaraBHosts[i+2];
                        return mostraRedes = retornoMascaraBRedes[i+2];
                    }
                    else if(classeC == 224) {
                        mostraHost = retornoMascaraBHosts[i+3];
                        return mostraRedes = retornoMascaraBRedes[i+3];
                    }
                    else if(classeC == 240) {
                        mostraHost = retornoMascaraBHosts[i+4];
                        return mostraRedes = retornoMascaraBRedes[i+4];
                    }
                    else if(classeC == 248) {
                        mostraHost = retornoMascaraBHosts[i+5];
                        return mostraRedes = retornoMascaraBRedes[i+5];
                    }
                    else if(classeC == 252) {
                        mostraHost = retornoMascaraBHosts[i+6];
                        return mostraRedes = retornoMascaraBRedes[i+6];
                    }
                    else if(classeC == 254) {
                        mostraHost = retornoMascaraBHosts[i+7];
                        return mostraRedes = retornoMascaraBRedes[i+7];
                    }

                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }
                }
            }
            else if( classeA == 255 && classeB == 255 && classeC == 255 && classeD <= 255){
                System.out.println("teste else if MASCARA_B *********************"+ classeC+" classe d "+ classeD);
                for (int i = 0; i <= contaBit[7];i ++){
                    if (classeC == 255 && classeD == 0) {
                        mostraHost = retornoMascaraBHosts[i+8];
                        return mostraRedes = retornoMascaraBRedes[i+8];
                    }
                    else if (classeD == 128) {//terminar a partir daqui
                        mostraHost = retornoMascaraBHosts[i+9];
                        return mostraRedes = retornoMascaraBRedes[i+9];
                    }
                    else if(classeD  == 192) {
                        mostraHost = retornoMascaraBHosts[i+10];
                        return mostraRedes = retornoMascaraBRedes[i+10];
                    }
                    else if(classeD  == 224) {
                        mostraHost = retornoMascaraBHosts[i+11];
                        return mostraRedes = retornoMascaraBRedes[i+11];
                    }
                    else if(classeD  == 240) {
                        mostraHost = retornoMascaraBHosts[i+12];
                        return mostraRedes = retornoMascaraBRedes[i+12];
                    }
                    else if(classeD  == 248) {
                        mostraHost = retornoMascaraBHosts[i+13];
                        return mostraRedes = retornoMascaraBRedes[i+13];
                    }
                    else if(classeD  == 252) {
                        mostraHost = retornoMascaraBHosts[i+14];
                        return mostraRedes = retornoMascaraBRedes[i+14];
                    }
                    else if(classeD  == 254) {
                        mostraHost = retornoMascaraBHosts[i+15];
                        return mostraRedes = retornoMascaraBRedes[i+15];
                    }
                    else if(classeD  == 255) {
                        mostraHost = "1";
                        return mostraRedes = "16777216";
                    }
                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }

                }
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle(" Dados inseridos errados ");
            alerta.setMessage("Valores introduzidos no campo Mascara Incorretos");
            alerta.setNegativeButton("Ok",null).show();
            Zera_Dados();
            return mostraRedes = "";
        }// fim else geral do comparaIpB
        /********************************** if da classe C************************************/
        else if (comparaIpC.equals(comparaMascara)){
            if( classeA == 255 && classeB == 255 && classeC == 255 && classeD <= 255){
                System.out.println("teste else if MASCARA_B *********************"+ classeC+" classe d "+ classeD);
                for (int i = 0; i <= contaBit[7];i ++){
                    if (classeC == 255 && classeD == 0) {
                        mostraHost = retornoMascaraCHosts[i];
                        return mostraRedes = retornoMascaraCRedes[i];
                    }
                    else if (classeD == 128) {//terminar a partir daqui
                        mostraHost = retornoMascaraCHosts[i+1];
                        return mostraRedes = retornoMascaraCRedes[i+1];
                    }
                    else if(classeD  == 192) {
                        mostraHost = retornoMascaraCHosts[i+2];
                        return mostraRedes = retornoMascaraCRedes[i+2];
                    }
                    else if(classeD  == 224) {
                        mostraHost = retornoMascaraCHosts[i+3];
                        return mostraRedes = retornoMascaraCRedes[i+3];
                    }
                    else if(classeD  == 240) {
                        mostraHost = retornoMascaraCHosts[i+4];
                        return mostraRedes = retornoMascaraCRedes[i+4];
                    }
                    else if(classeD  == 248) {
                        mostraHost = retornoMascaraCHosts[i+5];
                        return mostraRedes = retornoMascaraCRedes[i+5];
                    }
                    else if(classeD  == 252) {
                        mostraHost = retornoMascaraCHosts[i+6];
                        return mostraRedes = retornoMascaraCRedes[i+6];
                    }
                    else if(classeD  == 254) {
                        mostraHost = retornoMascaraCHosts[i+7];
                        return mostraRedes = retornoMascaraCRedes[i+7];
                    }
                    else if(classeD  == 255) {
                        mostraHost = "1";
                        return mostraRedes = "16777216";
                    }
                    else {
                        mostraHost = "Valores de SubNets Errados";
                        return mostraRedes = "Valores de SubNets Errados";
                    }

                }
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setTitle(" Dados inseridos errados ");
            alerta.setMessage("Valores introduzidos no campo Mascara Incorretos");
            alerta.setNegativeButton("Ok",null).show();
            Zera_Dados();
            return mostraRedes = "";


        }//fim do else if geral do comparaIpC
        return "dados inválidos";

    }// fim do toRedes

    /***************************************** Método para calcular máscara padrão*/
    public boolean calcularMascaraPadrao(int octal[]){

        if(octal[0] == 255 && octal[1] == 0 & octal[2] ==0 && octal[3] == 0){
            Toast.makeText(this, "Máscara padrão classe A: 255.0.0.0", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if(octal[0] == 255 && octal[1] == 255 && octal[2] ==0 && octal[3] == 0){
            Toast.makeText(this, "Máscara padrão classe B: 255.255.0.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(octal[0] == 255 && octal[1] == 255 && octal[2] == 255 && octal[3] == 0){
            Toast.makeText(this, "Máscara padrão classe C: 255.255.255.0", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(octal[0] == 255 && octal[1] == 255 && octal[2] == 255 && octal[3] == 255){
            Toast.makeText(this, "BroadCast: 255.255.255.255", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public String ToHost() {

        String mostra = mostraHost;
        return mostra;
    }

    public void Zera_Dados() {
        edIp.setText("");
        edMascara.setText("");
        edClasseDeIp.setText("");
        edTipoDeIp.setText("");
        edTotalHost.setText("");
        edTotalRedes.setText("");
        conta = 0;
    }

    public boolean ValidacaoIp(int a[]) {
        boolean valido = false;
        if (a[0] < 256 && a[1] < 256 && a[2] < 256 && a[3] < 256) valido = true;
        return valido;
    }

    public void DisplayToast(String b) {
        Toast.makeText(this,b,Toast.LENGTH_LONG).show();
    }

    public String Classe_Ip(int c) {
        String str = "";
        if (c > 0   && c < 128) str = "Classe A";
        else if (c > 127 && c < 192) str = "Classe B";
        else if (c > 191 && c < 225) str = "Classe C";
        else if (c > 224 && c < 240) str = "Classe D";
        else str = "Classe E";
        return str;
    }

    public String Tipo_Ip(int n1, int n2) {
        String str = "";

        if (n1 == 10) str = "O Tipo é Privado";
        else if (n1 == 172 && (n2 > 15 && n2 < 32)) str = "O Tipo é Privado";
        else if (n1 == 192 &&   n2 == 168) str = "O Tipo é Privado";
        else str = "O Tipo é Público";
        return str;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Programa:
                DisplayToast("App de Classificação de Ip");break;

            case R.id.Linguagem:
                DisplayToast("Android e Java");break;

            case R.id.Autores:
                DisplayToast("Antonio Filho e Débora M. Mutiz");break;
        }
        return super.onOptionsItemSelected(item);
    }
}