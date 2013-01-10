/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony;

import static com.android.internal.telephony.RILConstants.*;

import android.content.Context;
import android.os.Message;
import android.os.Parcel;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

/**
 * Custom Qualcomm No SimReady RIL for ZTE using the latest Uicc stack
 *
 * {@hide}
 */
public class ZTEQualcommUiccRIL extends ZTEQualcommRIL implements CommandsInterface {
    protected String mAid;
    protected boolean mUSIM;
    private int mSetPreferredNetworkType;
    private String mLastDataIface;
    boolean RILJ_LOGD = true;
    boolean RILJ_LOGV = false;

    public ZTEQualcommUiccRIL(Context context, int networkMode, int cdmaSubscription) {
        super(context, networkMode, cdmaSubscription);
    }

    @Override
    public void
    supplyIccPin(String pin, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN, result);


        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(2);
        rr.mp.writeString(pin);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    supplyIccPuk(String puk, String newPin, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK, result);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(3);
        rr.mp.writeString(puk);
        rr.mp.writeString(newPin);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    supplyIccPin2(String pin, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PIN2, result);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(2);
        rr.mp.writeString(pin);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    supplyIccPuk2(String puk, String newPin2, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_ENTER_SIM_PUK2, result);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(3);
        rr.mp.writeString(puk);
        rr.mp.writeString(newPin2);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    changeIccPin(String oldPin, String newPin, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN, result);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(3);
        rr.mp.writeString(oldPin);
        rr.mp.writeString(newPin);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    changeIccPin2(String oldPin2, String newPin2, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_CHANGE_SIM_PIN2, result);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        rr.mp.writeInt(3);
        rr.mp.writeString(oldPin2);
        rr.mp.writeString(newPin2);
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    getIMSI(Message result) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_GET_IMSI, result);

        rr.mp.writeInt(1);
        rr.mp.writeString(mAid);

        if (RILJ_LOGD) riljLog(rr.serialString() +
                              "> getIMSI:RIL_REQUEST_GET_IMSI " +
                              RIL_REQUEST_GET_IMSI +
                              " aid: " + mAid +
                              " " + requestToString(rr.mRequest));

        send(rr);
    }

    @Override
    public void
    setupDataCall(String radioTechnology, String profile, String apn,
            String user, String password, String authType, String protocol,
            Message result) {

        RILRequest rr
                = RILRequest.obtain(RIL_REQUEST_SETUP_DATA_CALL, result);
					if (RILJ_LOGD) riljLog(
                              "> RIL_REQUEST_SETUP_DATA_CALL " +
                              RIL_REQUEST_SETUP_DATA_CALL + requestToString(rr.mRequest) + "radiotechnology" + radioTechnology + "protocol" + protocol);
        rr.mp.writeInt(7);

        rr.mp.writeString(radioTechnology);
        rr.mp.writeString(profile);
        rr.mp.writeString(apn);
        rr.mp.writeString(user);
        rr.mp.writeString(password);
        rr.mp.writeString(authType);
        rr.mp.writeString(protocol); // ipVersion

        if (RILJ_LOGD) riljLog(rr.serialString() + "> "
                + requestToString(rr.mRequest) + " " + radioTechnology + " "
                + profile + " " + apn + " " + user + " "
                + password + " " + authType + " " + protocol);

        send(rr);
    }

    @Override
    public void
    iccIO (int command, int fileid, String path, int p1, int p2, int p3,
            String data, String pin2, Message result) {
        //Note: This RIL request has not been renamed to ICC,
        //       but this request is also valid for SIM and RUIM
        RILRequest rr
                = RILRequest.obtain(RIL_REQUEST_SIM_IO, result);

        if (mUSIM)
            path = path.replaceAll("7F20$","7FFF");

        rr.mp.writeInt(command);
        rr.mp.writeInt(fileid);
        rr.mp.writeString(path);
        rr.mp.writeInt(p1);
        rr.mp.writeInt(p2);
        rr.mp.writeInt(p3);
        rr.mp.writeString(data);
        rr.mp.writeString(pin2);
        rr.mp.writeString(mAid);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> iccIO: "
                    + " aid: " + mAid + " "
                    + requestToString(rr.mRequest)
                    + " 0x" + Integer.toHexString(command)
                    + " 0x" + Integer.toHexString(fileid) + " "
                    + " path: " + path + ","
                    + p1 + "," + p2 + "," + p3);

        send(rr);
    }
    @Override
    public void
    queryFacilityLock (String facility, String password, int serviceClass,
                            Message response) {
        RILRequest rr = RILRequest.obtain(RIL_REQUEST_QUERY_FACILITY_LOCK, response);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " aid: " + mAid + " facility: " + facility);

        // count strings
        rr.mp.writeInt(4);

        rr.mp.writeString(facility);
        rr.mp.writeString(password);

        rr.mp.writeString(Integer.toString(serviceClass));
        rr.mp.writeString(mAid);

        send(rr);
    }

    @Override
    public void
    setFacilityLock (String facility, boolean lockState, String password,
                        int serviceClass, Message response) {
        String lockString;
        RILRequest rr
                = RILRequest.obtain(RIL_REQUEST_SET_FACILITY_LOCK, response);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest)
                    + " aid: " + mAid + " facility: " + facility
                    + " lockstate: " + lockState);

        // count strings
        rr.mp.writeInt(5);

        rr.mp.writeString(facility);
        lockString = (lockState)?"1":"0";
        rr.mp.writeString(lockString);
        rr.mp.writeString(password);
        rr.mp.writeString(Integer.toString(serviceClass));
        rr.mp.writeString("");

        send(rr);
    }

    @Override
    protected Object
    responseIccCardStatus(Parcel p) {
        IccCardApplication ca;

        IccCardStatus status = new IccCardStatus();
        status.setCardState(p.readInt());
        status.setUniversalPinState(p.readInt());
        status.setGsmUmtsSubscriptionAppIndex(p.readInt());
        status.setCdmaSubscriptionAppIndex(p.readInt());

        status.setImsSubscriptionAppIndex(p.readInt());

        int numApplications = p.readInt();

        // limit to maximum allowed applications
        if (numApplications > IccCardStatus.CARD_MAX_APPS) {
            numApplications = IccCardStatus.CARD_MAX_APPS;
        }
        status.setNumApplications(numApplications);

        for (int i = 0; i < numApplications; i++) {
            ca = new IccCardApplication();
            ca.app_type = ca.AppTypeFromRILInt(p.readInt());
            ca.app_state = ca.AppStateFromRILInt(p.readInt());
            ca.perso_substate = ca.PersoSubstateFromRILInt(p.readInt());
            ca.aid = p.readString();
            ca.app_label = p.readString();
            ca.pin1_replaced = p.readInt();
            ca.pin1 = ca.PinStateFromRILInt(p.readInt());
            ca.pin2 = ca.PinStateFromRILInt(p.readInt());
            p.readInt(); //remaining_count_pin1
            p.readInt(); //remaining_count_puk1
            p.readInt(); //remaining_count_pin2
            p.readInt(); //remaining_count_puk2
            status.addApplication(ca);
        }
        int appIndex = -1;
        if (mPhoneType == RILConstants.CDMA_PHONE) {
            appIndex = status.getCdmaSubscriptionAppIndex();
            Log.d(LOG_TAG, "This is a CDMA PHONE " + appIndex);
        } else {
            appIndex = status.getGsmUmtsSubscriptionAppIndex();
            Log.d(LOG_TAG, "This is a GSM PHONE " + appIndex);
        }

        if (numApplications > 0) {
            IccCardApplication application = status.getApplication(appIndex);
            mAid = application.aid;
            mUSIM = application.app_type
                      == IccCardApplication.AppType.APPTYPE_USIM;
            mSetPreferredNetworkType = mPreferredNetworkType;

            if (TextUtils.isEmpty(mAid))
               mAid = "";
            Log.d(LOG_TAG, "mAid " + mAid);
        }

        return status;
    }

    @Override
    protected DataCallState getDataCallState(Parcel p, int version) {
        DataCallState dataCall = new DataCallState();

        	dataCall.version = 6;
			dataCall.status = p.readInt();
            dataCall.cid = p.readInt();
            dataCall.active = p.readInt();
            dataCall.type = p.readString();
            String unknown = p.readString();//possible ifname
			String addresses = p.readString();
			String dnses = p.readString();
			String gateways = p.readString();
						
			dataCall.ifname = "rmnet0" ;
			
                    
            if (!TextUtils.isEmpty(addresses)) {
                dataCall.addresses = addresses.split(" ");
            }
            
            if (!TextUtils.isEmpty(dnses)) {
                dataCall.dnses = dnses.split(" ");
            }
            
            if (!TextUtils.isEmpty(gateways)) {
                dataCall.gateways = gateways.split(" ");
            }
			if (RILJ_LOGD) riljLog("DataCallstate got addresses=" + addresses);
                if (RILJ_LOGD) riljLog("DataCallstate got dnses=" + dnses);
		
                if (RILJ_LOGD) riljLog("DataCallstate got gateways=" + gateways);	
        return dataCall;
    }
	@Override
	protected Object
    responseDataCallList(Parcel p) {
        ArrayList<DataCallState> response;
        boolean oldRil = needsOldRilFeature("datacall");
        int ver = p.readInt();
        int num = p.readInt();
        riljLog("responseDataCallList ver=" + ver + " num=" + num);

        response = new ArrayList<DataCallState>(num);
        for (int i = 0; i < num; i++) {
            response.add(getDataCallState(p, ver));
        }

        return response;
    }
	
    @Override
    protected Object
    responseSetupDataCall(Parcel p) {
        int ver = p.readInt();
        int num = p.readInt();
				
        if (RILJ_LOGV) riljLog("responseSetupDataCall ver= " + ver + " num= " + num );
		DataCallState dataCall;

        dataCall = new DataCallState();
		dataCall.version = ver;
		dataCall.status = p.readInt(); //status
		dataCall.cid = p.readInt(); //cid
		dataCall.active = p.readInt(); //active
		String unknown5 = p.readString(); //type;
		if (RILJ_LOGD) riljLog("responseSetupDataCall type(unknwn5) = " + unknown5);
		mLastDataIface = p.readString(); //ifname mLastDataIface;
		if (RILJ_LOGD) riljLog("responseSetupDataCall mLastDataIface = " + mLastDataIface);
		dataCall.ifname = "rmnet0";//p.readString();
		String addresses = p.readString();
		String dnses = p.readString();
		String gateways = p.readString();
	
        if (TextUtils.isEmpty(dataCall.ifname)) {
            throw new RuntimeException(
                    "RIL_REQUEST_SETUP_DATA_CALL response, no ifname");
        }
        
        if (!TextUtils.isEmpty(addresses)) {
          dataCall.addresses = addresses.split(" ");
        }
				if (RILJ_LOGD) riljLog("responseSetupDataCall got addresses=" + addresses);
                if (RILJ_LOGD) riljLog("responseSetupDataCall got dnses=" + dnses);
		
                if (RILJ_LOGD) riljLog("responseSetupDataCall got gateways=" + gateways);		
        
		//dataCall = getDataCallState(p, ver);
        return dataCall;
    }
	
    @Override
    public void getNeighboringCids(Message response) {
        if (!getRadioState().isOn())
            return;

        RILRequest rr = RILRequest.obtain(
                RILConstants.RIL_REQUEST_GET_NEIGHBORING_CELL_IDS, response);

        if (RILJ_LOGD) riljLog(rr.serialString() + "> " + requestToString(rr.mRequest));

        send(rr);
    }

    @Override
    public void setCurrentPreferredNetworkType() {
        if (RILJ_LOGD) riljLog("setCurrentPreferredNetworkType: " + mSetPreferredNetworkType);
        setPreferredNetworkType(mSetPreferredNetworkType, null);
    }

    @Override
    public void setPreferredNetworkType(int networkType , Message response) {
        /**
          * If not using a USIM, ignore LTE mode and go to 3G
          */
        if (!mUSIM && networkType == RILConstants.NETWORK_MODE_LTE_GSM_WCDMA) {
            networkType = RILConstants.NETWORK_MODE_WCDMA_PREF;
        }
        mSetPreferredNetworkType = networkType;
        super.setPreferredNetworkType(networkType, response);
    }

    @Override
    protected Object
    responseSignalStrength(Parcel p) {
        int numInts = 12;
        int response[];

        boolean oldRil = needsOldRilFeature("signalstrength");
        boolean noLte = false;

        /* TODO: Add SignalStrength class to match RIL_SignalStrength */
        response = new int[numInts];
        for (int i = 0 ; i < numInts ; i++) {
            if ((oldRil || noLte) && i > 6 && i < 12) {
                response[i] = -1;
            } else {
                response[i] = p.readInt();
            }
            if (i == 7 && response[i] == 99) {
                response[i] = -1;
                noLte = true;
            }
            if (i == 8 && !noLte) {
                response[i] *= -1;
            }
        }

        return response;
    }

}
