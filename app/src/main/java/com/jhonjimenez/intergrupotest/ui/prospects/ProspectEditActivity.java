package com.jhonjimenez.intergrupotest.ui.prospects;

import android.content.Intent;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.material.textfield.TextInputLayout;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.adapters.ProspectStatusAdapter;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.utils.Constants;
import com.jhonjimenez.intergrupotest.utils.Status;
import com.jhonjimenez.intergrupotest.utils.Validations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProspectEditActivity extends AppCompatActivity {

    @BindView(R.id.textinputlayout_name)
    TextInputLayout textInputLayoutName;
    @BindView(R.id.edittext_name)
    EditText editTextName;
    @BindView(R.id.textinputlayout_lastname)
    TextInputLayout textInputLayoutLastName;
    @BindView(R.id.edittext_lastname)
    EditText editTextLastName;
    @BindView(R.id.textinputlayout_document)
    TextInputLayout textInputLayoutDocument;
    @BindView(R.id.edittext_document)
    EditText editTextDocument;
    @BindView(R.id.textinputlayout_phone)
    TextInputLayout textInputLayoutPhone;
    @BindView(R.id.edittext_phone)
    EditText editTextPhone;
    @BindView(R.id.spinner_status)
    Spinner spinnerStatus;

    private Prospect prospect;
    private ProspectStatusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospect_edit);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        adapter = new ProspectStatusAdapter(this, createStatuses());
        spinnerStatus.setAdapter(adapter);

        Intent intent = getIntent();
        prospect = (Prospect) intent.getSerializableExtra(Constants.INTENT);
        if (prospect != null) {
            setDataProspect(prospect);
        }
    }

    private List<Status> createStatuses() {
        List<Status> statuses = new ArrayList<>();
        statuses.add(new Status(Constants.PENDING, "Pendiente"));
        statuses.add(new Status(Constants.APPROVED, "Aprovado"));
        statuses.add(new Status(Constants.ACCEPTED, "Aceptado"));
        statuses.add(new Status(Constants.REJECTED, "Rechazado"));
        statuses.add(new Status(Constants.DISABLED, "Deshabilitado"));

        return statuses;
    }

    private void setDataProspect(Prospect prospect) {

        spinnerStatus.setSelection(prospect.getStatusCd());

        editTextName.setText(prospect.getName());
        editTextLastName.setText(prospect.getSurname());
        editTextDocument.setText(prospect.getSchProspectIdentification());
        editTextPhone.setText(prospect.getTelephone());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.button_edit)
    public void validateInputs() {
        int error = 0;

        error += Validations.validateEditText(editTextName, textInputLayoutName, this);
        error += Validations.validateEditText(editTextLastName, textInputLayoutLastName, this);
        error += Validations.validateEditText(editTextDocument, textInputLayoutDocument, this);
        error += Validations.validateEditText(editTextPhone, textInputLayoutPhone, this);


        if (error == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Editar");
            builder.setMessage("Esta seguro de hacer esta edición.")
                    .setPositiveButton("Aceptar", (dialog1, id1) -> {

                        Status objecStatus = adapter.getItem(spinnerStatus.getSelectedItemPosition());

                        prospect.setName(editTextName.getText().toString());
                        prospect.setSurname(editTextLastName.getText().toString());
                        prospect.setSchProspectIdentification(editTextDocument.getText().toString());
                        prospect.setTelephone(editTextPhone.getText().toString());
                        prospect.setStatusCd(objecStatus.getId());

                        Intent intentResult = new Intent();
                        intentResult.putExtra(Constants.INTENT, prospect);
                        setResult(RESULT_OK, intentResult);
                        finish();

                    }).setNegativeButton("cancelar", (dialog, which) -> dialog.dismiss());

            builder.create().show();
        }
    }
}
