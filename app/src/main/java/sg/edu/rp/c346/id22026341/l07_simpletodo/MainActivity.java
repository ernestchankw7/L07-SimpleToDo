package sg.edu.rp.c346.id22026341.l07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etToDo;
    Button btnAdd;
    Button btnDel;
    Button btnClear;
    ListView lvToDo;
    Spinner spn;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDel = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.listViewToDo);
        spn = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();
        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alToDo);
        lvToDo.setAdapter(aaToDo);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newToDo = etToDo.getText().toString();
                                alToDo.add(newToDo);
                                aaToDo.notifyDataSetChanged();
                                etToDo.setText(null);
                            }
                        });
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(!etToDo.getText().toString().isEmpty()) // Position number entered by user
                                {
                                    int pos = Integer.parseInt(etToDo.getText().toString());
//                                    if(etToDo.getText().toString().isEmpty())
//                                    {
//                                        Toast.makeText(MainActivity.this,"Input a index number",Toast.LENGTH_SHORT).show();
//                                    }
                                    if(pos>alToDo.size())
                                    {
                                        Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        //int pos = Integer.parseInt(etToDo.getText().toString());
                                        alToDo.remove(pos);
                                        aaToDo.notifyDataSetChanged();
                                        etToDo.setText(null);
                                    }
                                } else {
                                    Toast.makeText(MainActivity.this, "You do not have any task to remove", Toast.LENGTH_SHORT).show();
                                }
//                                if(alToDo.isEmpty())
//                                {
//                                    Toast.makeText(MainActivity.this,"You do nott have any task to remove",Toast.LENGTH_SHORT).show();
//                                }
//                                else if(pos>alToDo.size())
//                                {
//                                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    //int pos = Integer.parseInt(etToDo.getText().toString());
//                                    alToDo.remove(pos);
//                                    aaToDo.notifyDataSetChanged();
//                                    etToDo.setText(null);
//                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String newToDo = etToDo.getText().toString();
//                alToDo.add(newToDo);
//                aaToDo.notifyDataSetChanged();
//                etToDo.setText(null);
//            }
//        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });
    }
}