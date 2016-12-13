package com.example.helloworld.fragments;

import com.example.helloworld.HelloWorldActivity;
import com.example.helloworld.LoginActivity;
import com.example.helloworld.R;
import com.example.helloworld.fragments.inputcells.SimpleTextInputCellFragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PasswordRecoverStep2Fragment extends Fragment {
	SimpleTextInputCellFragment fragPassword, fragPasswordRepeat, fragVerify;
	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if(view==null){
			view = inflater.inflate(R.layout.fragment_password_recover_step2, null);
			fragVerify = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_verify);
			fragPassword = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password);
			fragPasswordRepeat = (SimpleTextInputCellFragment) getFragmentManager().findFragmentById(R.id.input_password_repeat);

			view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					onSubmitClicked();
				}
			});
		}

		return view;
	}

	public String getText(){
		return fragPassword.getText();
	}
	
	public void onResume() {
		super.onResume();
        
		fragVerify.setLabelText("��֤��");
		fragVerify.setHintText("��������֤��");
		fragPassword.setLabelText("����������");
		fragPassword.setHintText("������������");
		fragPasswordRepeat.setLabelText("�ظ�������");
		fragPasswordRepeat.setHintText("���ظ�����������");
	}

	public static interface OnSubmitClickedListener{
		void onSubmitClicked();
	}

	OnSubmitClickedListener onSubmitClickedListener;

	public void setOnSubmitClickedListener(OnSubmitClickedListener onSubmitClickedListener) {
		this.onSubmitClickedListener = onSubmitClickedListener;
	}

	void onSubmitClicked(){
		if(fragPassword.getText().equals(fragPasswordRepeat.getText())){
			if(onSubmitClickedListener!=null){
				onSubmitClickedListener.onSubmitClicked();
				new AlertDialog.Builder(getActivity()).setMessage("�ѳɹ��޸����룡").setPositiveButton("OK", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent itnt = new Intent(getActivity(), LoginActivity.class);
						startActivity(itnt);	
					}	
				}).show();
			}
		}else{
			new AlertDialog.Builder(getActivity()).setMessage("������������벻ͬ�����������룡").show();
		}
	}
}