import classNames from 'classnames/bind';
import { useEffect, useState } from 'react';
import { Offcanvas } from 'react-bootstrap';
import { useLocation, useParams } from 'react-router-dom';
import Button from '~/components/Button';
import MyTable from '~/components/MyTable';
import doctorService from '~/services/doctor.service';
import convertTimestamp from '~/utils/convertTimestamp';

import styles from './Certificate.module.scss';

const cx = classNames.bind(styles);

function Certificate() {
    const { state } = useLocation();
    const { cerId } = useParams();
    const [cers, setCers] = useState([]);
    const [prescriptions, setPrescriptions] = useState([]);
    const [preId, setPreId] = useState();
    const [statusPre, setStatusPre] = useState('');
    const [statusMedicine, setStatusMedicine] = useState('');
    const [medicines, setMedicines] = useState([]);
    const [changePre, setChangePre] = useState(0);
    const [changeMedicine, setChangeMedicine] = useState(0);
    const [medicineOfPres, setMedicineOfPres] = useState([{ id: null, mds: null }]);
    const [quantity, setQuantity] = useState(0);
    const [isChange, setIsChange] = useState(false);

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    useEffect(() => {
        doctorService.getCertificateById(cerId).then(
            (res) => setCers(res.data),
            (error) => {
                setStatusPre(error.response.status);
                setPrescriptions(null);
            },
        );
    }, []);

    useEffect(() => {
        doctorService.getPrescriptionsByCertificateId(cerId).then((res) => {
            setPrescriptions(res.data);
        });
    }, [changePre]);

    useEffect(() => {
        if (prescriptions.length !== 0) {
            setMedicineOfPres([]);
            prescriptions.forEach((p) =>
                doctorService.getPrescriptionDetailsByPrescriptionId(p.id).then(
                    (medicines) => setMedicineOfPres((prev) => [...prev, { id: p.id, mds: medicines.data }]),
                    (error) => setMedicineOfPres((prev) => [...prev, { id: p.id, mds: null }]),
                ),
            );
        }
    }, [prescriptions, changeMedicine]);

    const createPrescription = () => {
        doctorService.createPrescription(cerId).then((res) => setChangePre(changePre + 1));
    };

    const deletePrescription = (preId) => {
        doctorService.deletePrescription(preId).then((res) => {
            if (res.data) {
                setChangePre(changePre + 1);
            }
        });
    };

    const getMedicine = (preId) => {
        doctorService.getMedicines().then((medicine) => {
            setMedicines(medicine.data);
            setPreId(preId);
        });
        console.log('hey');
    };

    const addMedicineToPresciption = (medicineId) => {
        doctorService.addMedicineToPresciption(preId, medicineId).then((res) => setChangeMedicine(changeMedicine + 1));
    };

    const removeMedicine = (preId, medicineId) => {
        doctorService
            .removeMedicineFromPresciption(preId, medicineId)
            .then((res) => setChangeMedicine(changeMedicine + 1));
    };

    return (
        <div>
            <h1>M?? b???nh nh??n: {state.register.id}</h1>
            <h1>T??n b???nh nh??n: {state.register.name}</h1>
            <br />
            {cers && (
                <>
                    <h1>M?? phi???u kh??m: {cers.id}</h1>
                    <h2>Tri???u ch???ng: {cers.symptom}</h2>
                    <h2>K???t lu???n: {cers.conclusion}</h2>
                </>
            )}

            {prescriptions ? (
                <>
                    {prescriptions.map((pre) => (
                        <>
                            <div key={pre.id} className={cx('wrap-pre')}>
                                <div className={cx('pre-item')}>
                                    <h4>M?? phi???u kh??m</h4>
                                    <span>{pre.id}</span>
                                </div>
                                <div className={cx('pre-item')}>
                                    <h4>Ng??y t???o</h4>
                                    <span>{convertTimestamp(pre.createdDate)}</span>
                                </div>
                                <Button
                                    small
                                    login
                                    onClick={() => {
                                        handleShow();
                                        getMedicine(pre.id);
                                    }}
                                >
                                    Th??m thu???c
                                </Button>
                                <Button
                                    small
                                    login
                                    primary
                                    onClick={() => {
                                        deletePrescription(pre.id);
                                    }}
                                >
                                    X??a
                                </Button>
                            </div>
                            {medicineOfPres.find((m) => m.id === pre.id) && (
                                <MyTable
                                    small
                                    headings={[
                                        'M?? thu???c',
                                        'T??n thu???c',
                                        'Ghi ch??',
                                        '????n v???',
                                        'S??? l?????ng / ????n v???',
                                        'S??? l?????ng',
                                    ]}
                                >
                                    {medicineOfPres.find((m) => m.id === pre.id).mds !== null &&
                                        medicineOfPres
                                            .find((m) => m.id === pre.id)
                                            .mds.map((m) => (
                                                <tr key={m.medicine.id}>
                                                    <td>{m.medicine.id}</td>
                                                    <td>{m.medicine.name}</td>
                                                    <td>{m.medicine.note}</td>
                                                    <td>{m.medicine.unit.name}</td>
                                                    <td>{m.medicine.quantityPerUnit}</td>
                                                    <td>{m.quantity}</td>
                                                    <td>
                                                        <Button
                                                            small
                                                            primary
                                                            onClick={() => removeMedicine(pre.id, m.medicine.id)}
                                                        >
                                                            X??a
                                                        </Button>
                                                    </td>
                                                </tr>
                                            ))}
                                </MyTable>
                            )}
                        </>
                    ))}
                    {medicines.length && (
                        <Offcanvas show={show} onHide={handleClose} placement="top">
                            <Offcanvas.Header closeButton>
                                <Offcanvas.Title>Toa thu???c</Offcanvas.Title>
                            </Offcanvas.Header>
                            <Offcanvas.Body>
                                <MyTable
                                    headings={[
                                        'M?? thu???c',
                                        'T??n thu???c',
                                        'C??ch d??ng',
                                        'Gi??',
                                        '????n v???',
                                        'S??? l?????ng/????n v???',
                                    ]}
                                >
                                    {medicines.map((medicine) => (
                                        <tr
                                            className={cx('add-medicine')}
                                            onClick={() => addMedicineToPresciption(medicine.id)}
                                            key={medicine.id}
                                        >
                                            <td>{medicine.id}</td>
                                            <td>{medicine.name}</td>
                                            <td>{medicine.note}</td>
                                            <td>{medicine.price}</td>
                                            <td>{medicine.unit.name}</td>
                                            <td>{medicine.quantityPerUnit}</td>
                                        </tr>
                                    ))}
                                </MyTable>
                            </Offcanvas.Body>
                        </Offcanvas>
                    )}
                </>
            ) : (
                <h3>Ch??a c?? toa thu??c</h3>
            )}

            <Button onClick={createPrescription} login>
                Th??m toa thu???c
            </Button>
        </div>
    );
}

export default Certificate;
