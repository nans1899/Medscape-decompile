package com.google.firebase.iid;

import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.firebase:firebase-iid@@20.3.0 */
interface IMessengerCompat extends IInterface {
    void send(Message message) throws RemoteException;

    /* compiled from: com.google.firebase:firebase-iid@@20.3.0 */
    public static class Proxy implements IMessengerCompat {
        private final IBinder remote;

        Proxy(IBinder iBinder) {
            this.remote = iBinder;
        }

        public void send(Message message) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            obtain.writeInterfaceToken(IMessengerCompat.DESCRIPTOR);
            obtain.writeInt(1);
            message.writeToParcel(obtain, 0);
            try {
                this.remote.transact(1, obtain, (Parcel) null, 1);
            } finally {
                obtain.recycle();
            }
        }

        public IBinder asBinder() {
            return this.remote;
        }
    }

    /* compiled from: com.google.firebase:firebase-iid@@20.3.0 */
    public static class Impl extends Binder implements IMessengerCompat {
        private final Handler handler;

        Impl(Handler handler2) {
            this.handler = handler2;
            attachInterface(this, IMessengerCompat.DESCRIPTOR);
        }

        public IBinder asBinder() {
            return this;
        }

        public void send(Message message) throws RemoteException {
            message.arg2 = Binder.getCallingUid();
            this.handler.dispatchMessage(message);
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            parcel.enforceInterface(getInterfaceDescriptor());
            if (i != 1) {
                return false;
            }
            send(parcel.readInt() != 0 ? (Message) Message.CREATOR.createFromParcel(parcel) : null);
            return true;
        }
    }
}
