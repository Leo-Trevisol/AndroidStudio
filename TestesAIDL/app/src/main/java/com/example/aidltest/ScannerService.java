package com.example.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.widget.Toast;

public class ScannerService extends Service {
    private static final java.lang.String DESCRIPTOR = "com.example.aidltest.IScanInterface";
    private android.os.IBinder mRemote;

    public ScannerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    IScanInterface.Stub mBinder = new IScanInterface.Stub() {
        @Override
        public void sendKeyEvent(KeyEvent key) throws RemoteException {

        }

        @Override
        public void scan() throws RemoteException {
            Toast.makeText(ScannerService.this, "AAA", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void stop() throws RemoteException {

        }

        @Override
        public int getScannerModel() throws RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            int _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                boolean _status = mRemote.transact(Stub.TRANSACTION_getScannerModel, _data, _reply, 0);
                if (!_status && getDefaultImpl() != null) {
                    return getDefaultImpl().getScannerModel();
                }
                _reply.readException();
                _result = _reply.readInt();
            }
            finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

    };
}