package sv.edu.itca.nr_conversorunidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spnUnidadesPeso;
    private Button btnConvertirPeso;
    private String strValor;
    private TextView txtGramos;
    private TextView txtLibras;
    private TextView txtKilos;
    private TextView txtToneladas;

    private final int GRAMO=0, LIBRA=1, KILO=2, TONELADA=3;
    private final int QUINIENTOS=500, MIL=1000, DOSMIL=2000, MILLON= 1000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarVariables();
        configurarBtnConvertir();

        Resources res= getResources();
        TabHost tabControl=(TabHost) findViewById(R.id.miTabHost);
        //Preparar el TabHost para incluir las nuevas pestañas
        tabControl.setup();

        TabHost.TabSpec spec;

        spec = tabControl.newTabSpec("PESO");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",res.getDrawable(R.drawable.ic_menu_peso));
        tabControl.addTab(spec);

        spec = tabControl.newTabSpec("TEMP");
        spec.setContent(R.id.tab2);
        spec.setIndicator("",res.getDrawable(R.drawable.ic_menu_temperatura));
        tabControl.addTab(spec);

        spec = tabControl.newTabSpec("LONG");
        spec.setContent(R.id.tab3);
        spec.setIndicator("",res.getDrawable(R.drawable.ic_menu_longitud));
        tabControl.addTab(spec);
        }

    private void IniciarVariables() {
        txtGramos = (TextView) findViewById(R.id.txtGramos);
        txtLibras = (TextView) findViewById(R.id.txtLibras);
        txtKilos = (TextView) findViewById(R.id.txtKilos);
        txtToneladas = (TextView) findViewById(R.id.txtToneladas);
    }

    private void configurarBtnConvertir() {
        btnConvertirPeso = (Button) findViewById(R.id.btnConvertir);
        btnConvertirPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText valorPesoTxt=(EditText) findViewById(R.id.txtValorPeso);
                strValor = valorPesoTxt.getText().toString();
                Float valorConvertir = Float.parseFloat(strValor);
                spnUnidadesPeso= (Spinner) findViewById(R.id.spinnerUnidades);
                int itemSeleccionado=spnUnidadesPeso.getSelectedItemPosition();

                switch (itemSeleccionado) {
                    case GRAMO:
                        txtGramos.setText(strValor);
                        txtLibras.setText(gramoLibras(valorConvertir));
                        txtKilos.setText(gramoKilos(valorConvertir));
                        txtToneladas.setText(gramoToneladas(valorConvertir));
                        break;
                    case LIBRA:
                        txtGramos.setText(libraGramos(valorConvertir));
                        txtLibras.setText(strValor);
                        txtKilos.setText(libraKilos(valorConvertir));
                        txtToneladas.setText(libraToneladas(valorConvertir));
                        break;
                    case KILO:
                        txtGramos.setText(kiloGramos(valorConvertir));
                        txtLibras.setText(kiloLibras(valorConvertir));
                        txtKilos.setText(strValor);
                        txtToneladas.setText(kiloToneladas(valorConvertir));
                        break;
                    case TONELADA:
                        txtGramos.setText(toneladaGramos(valorConvertir));
                        txtLibras.setText(toneladaLibras(valorConvertir));
                        txtKilos.setText(toneladaKilos(valorConvertir));
                        txtToneladas.setText(strValor);
                        break;
                }
            }
        });
    }




    //Convertir de Gramos a las otras unidades
    private String gramoLibras (Float valorConvertir) {
        return String.valueOf(valorConvertir/QUINIENTOS);
    }

    private String gramoKilos (Float valorConvertir) {
        return String.valueOf(valorConvertir/MIL);
    }

    private String gramoToneladas (Float valorConvertir) {
        return String.valueOf(valorConvertir/MILLON);
    }

    //Convetir de Libras a las otras unidades
    private String libraGramos (Float valorConvertir) {
        return String.valueOf(valorConvertir*QUINIENTOS);
    }

    private String libraKilos (Float valorConvertir) {
        return String.valueOf(valorConvertir/2);
    }

    private String libraToneladas (Float valorConvertir) {
        return String.valueOf(valorConvertir/DOSMIL);
    }

    //Convertir de Kilos a las otras unidades
    private String kiloGramos (Float valorConvertir) {
        return String.valueOf((valorConvertir*MIL));
    }

    private String kiloLibras (Float valorConvertir) {
        return String.valueOf((valorConvertir*2));
    }

    private String kiloToneladas (Float valorConvertir) {
        return String.valueOf((valorConvertir/MIL));
    }

    //Convertir de Toneladas a las otras unidades
    private String toneladaGramos (Float valorConvertir) {
        return String.valueOf(valorConvertir*MILLON);
    }

    private String toneladaLibras (Float valorConvertir) {
        return String.valueOf(valorConvertir*DOSMIL);
    }

    private String toneladaKilos (Float valorConvertir) {
        return String.valueOf(valorConvertir*MIL);
    }
}