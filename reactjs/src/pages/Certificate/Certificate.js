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
            <h1>Mã bệnh nhân: {state.register.id}</h1>
            <h1>Tên bệnh nhân: {state.register.name}</h1>
            <br />
            {cers && (
                <>
                    <h1>Mã phiếu khám: {cers.id}</h1>
                    <h2>Triệu chứng: {cers.symptom}</h2>
                    <h2>Kết luận: {cers.conclusion}</h2>
                </>
            )}

            {prescriptions ? (
                <>
                    {prescriptions.map((pre) => (
                        <>
                            <div key={pre.id} className={cx('wrap-pre')}>
                                <div className={cx('pre-item')}>
                                    <h4>Mã phiếu khám</h4>
                                    <span>{pre.id}</span>
                                </div>
                                <div className={cx('pre-item')}>
                                    <h4>Ngày tạo</h4>
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
                                    Thêm thuốc
                                </Button>
                                <Button
                                    small
                                    login
                                    primary
                                    onClick={() => {
                                        deletePrescription(pre.id);
                                    }}
                                >
                                    Xóa
                                </Button>
                            </div>
                            {medicineOfPres.find((m) => m.id === pre.id) && (
                                <MyTable
                                    small
                                    headings={[
                                        'Mã thuốc',
                                        'Tên thuốc',
                                        'Ghi chú',
                                        'Đơn vị',
                                        'Số lượng / đơn vị',
                                        'Số lượng',
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
                                                            Xóa
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
                                <Offcanvas.Title>Toa thuốc</Offcanvas.Title>
                            </Offcanvas.Header>
                            <Offcanvas.Body>
                                <MyTable
                                    headings={[
                                        'Mã thuốc',
                                        'Tên thuốc',
                                        'Cách dùng',
                                        'Giá',
                                        'Đơn vị',
                                        'Số lượng/đơn vị',
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
                <h3>Chưa có toa thuóc</h3>
            )}

            <Button onClick={createPrescription} login>
                Thêm toa thuốc
            </Button>
        </div>
    );
}

export default Certificate;
