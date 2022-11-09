import React, {useContext, useState, Fragment} from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import AuthContext from "../../store/auth-context";
import classes from "./User.module.css";
import Card from "../../utils/Card";
import MyConsumption from "../../components/device/MyConsumption";
import BarChart from "../../components/chart/BarChart";
import {NavLink} from "react-router-dom";

const Consumption = () => {
    const [startDate, setStartDate] = useState(new Date());
    const loginCtx = useContext(AuthContext);
    const [devices, setDevices] = useState([]);
    const [showData, setShowData] = useState(false);

    const fetchDevices = async () => {

        const response = await fetch(`http://localhost:8080/api/user/consumption/
        ${loginCtx.userId}/${startDate.toISOString().split('T')[0]}`);

        if (!response.ok) {
            throw new Error("Something went wrong");
        }
        const responseData = await response.json();
        const loadedDevices = [];

        for(const key in responseData) {
            loadedDevices.push({
                id: responseData[key].id,
                date: responseData[key].date,
                hour: responseData[key].hour,
                energyConsumption: responseData[key].energyConsumption,
                deviceId: responseData[key].deviceId
            });
        }
        setDevices(loadedDevices);
    }

    const send = () => {
        fetchDevices().then(() => {setShowData(true)}).catch((error) => {})
    }

    const deviceList = devices.map(device => <MyConsumption key={device.id} id={device.id} date={device.date} hour={device.hour} energyConsumption={device.energyConsumption} deviceId={device.deviceId}/>);
    return(
        <div>
            <h1 className={classes.h1}>Energy Consumption Page</h1>
            <div className={classes.topnav}>
                <NavLink to={'/'}>Home</NavLink>
                <NavLink to={'/seeDevices'}>My devices</NavLink>
            </div>
            <Card>
                <div className={classes.confirm}>
                    <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
                    <button className={classes.buttonAdd} onClick={send}>Confirm</button>
                </div>
            </Card>
            {showData &&
            <Card>
                <div className={classes.div}>
                    <p>Date</p>
                    <p>Hour</p>
                    <p>Energy Consumption</p>
                    <p>Device</p>
                </div>
                <ul className={classes.ul}>
                    {deviceList}
                </ul>
                <BarChart values={devices}/>
            </Card>}
        </div>
    );
}
export default Consumption