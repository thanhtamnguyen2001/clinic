import { useEffect, useState } from 'react';
import { Offcanvas, Table } from 'react-bootstrap';
import { useLocation, useNavigate } from 'react-router-dom';
import classNames from 'classnames/bind';

import styles from './Examination.mudule.scss';

import Button from '~/components/Button';

import convertTimestamp from '~/utils/convertTimestamp';

import DoctorService from '~/services/doctor.service';
import config from '~/config';
import doctorService from '~/services/doctor.service';
import Input from '~/components/Input';
import MyTable from '~/components/MyTable';

const cx = classNames.bind(styles);

function Examination() {
    const [certificates, setCertificates] = useState([]);
    const [changeCer, setChangeCer] = useState(0);
    const [statusCer, setStatusCer] = useState();
    const [idRegister, setIdRegister] = useState(null);
    const [idCer, setIdCer] = useState(null);

    const [symptom, setSymptom] = useState('');
    const [conclusion, setConclusion] = useState('');

    const [show, setShow] = useState(false);

    const { state } = useLocation();

    let navigate = useNavigate();

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    console.log('show ', show);

    useEffect(() => {
        DoctorService.getCertificatesByRegisterId(state.register.id).then(
            (res) => setCertificates(res.data),
            (error) => setStatusCer(error.response.status),
        );
    }, [changeCer]);

    const createCertificate = (e) => {
        e.preventDefault();
        let cerData = Object.fromEntries(new FormData(e.target).entries());

        doctorService.createCertificate(idRegister, cerData).then((cer) => {
            if (cer.status === 201) {
                setChangeCer(changeCer + 1);
            }
        });

        setIdRegister(null);
    };

    const updateCertificate = (e) => {
        e.preventDefault();
        let cerData = Object.fromEntries(new FormData(e.target).entries());

        doctorService.updateCertificate(idCer, cerData).then((cer) => {
            if (cer.status === 200) {
                setChangeCer(changeCer + 1);
            }
        });

        setIdCer(null);
    };

    const deleteCertificate = (certificateId) => {
        let result = doctorService.deleteCertificate(certificateId).then((res) => {
            if (result) {
                setChangeCer(changeCer + 1);
            }
        });
    };

    const cerDetailt = (cerId) => {
        return navigate(`${config.routes.cer}/${cerId}`, { state: state });
    };

    return (
        <div>
            <h1>Patient name: {state.register.name}</h1>
            <h3>Health Issues: {state.register.healthIssues}</h3>
            <MyTable
                title={'Phi???u kh??m c???a b???nh nh??n'}
                headings={['M?? phi???u kh??m', 'Tri???u ch???ng', 'K???t lu???n', 'B??c s??', 'Ng??y t???o']}
            >
                {statusCer === 404 ? (
                    <h3>B???nh nh??n ch??a c?? phi???u kh??m</h3>
                ) : (
                    <>
                        {certificates.map((cer, index) => (
                            <tr key={index} className={cx('cer')}>
                                <td>{cer.id}</td>
                                <td>{cer.symptom}</td>
                                <td>{cer.conclusion}</td>
                                <td>
                                    {cer.user.lastName} {cer.user.firstName}
                                </td>
                                <td>{convertTimestamp(cer.createdDate)}</td>
                                <td>
                                    <Button
                                        login
                                        small
                                        onClick={() => {
                                            handleShow();
                                            setIdRegister(null);
                                            setIdCer(cer.id);
                                            setConclusion(cer.conclusion);
                                            setSymptom(cer.symptom);
                                        }}
                                    >
                                        Ch???nh s???a
                                    </Button>
                                </td>
                                <td>
                                    <Button login small onClick={() => deleteCertificate(cer.id)}>
                                        X??a
                                    </Button>
                                </td>
                                <td>
                                    <Button
                                        onClick={() => {
                                            cerDetailt(cer.id);
                                        }}
                                        small
                                        login
                                    >
                                        Chi ti???t
                                    </Button>
                                </td>
                            </tr>
                        ))}
                    </>
                )}
            </MyTable>

            <Button
                login
                small
                onClick={() => {
                    handleShow();
                    setIdRegister(state.register.id);
                    setIdCer(null);
                }}
            >
                T???o phi???u kh??m
            </Button>

            {idRegister && (
                <Offcanvas show={show} onHide={handleClose} placement="top">
                    <Offcanvas.Header closeButton>
                        <Offcanvas.Title>Toa thu???c</Offcanvas.Title>
                    </Offcanvas.Header>
                    <Offcanvas.Body>
                        <form onSubmit={createCertificate}>
                            <Input name="symptom" placeholder="Tri???u ch???ng..." />
                            <Input name="conclusion" placeholder="K???t lu???n..." />
                            <Button>T???o phi???u kh??m</Button>
                        </form>
                    </Offcanvas.Body>
                </Offcanvas>
            )}
            {idCer && (
                <Offcanvas show={show} onHide={handleClose} placement="top">
                    <Offcanvas.Header closeButton>
                        <Offcanvas.Title>Toa thu???c</Offcanvas.Title>
                    </Offcanvas.Header>
                    <Offcanvas.Body>
                        <form onSubmit={updateCertificate}>
                            <Input
                                value={symptom}
                                name="symptom"
                                placeholder="Tri???u ch???ng..."
                                onChange={(e) => setSymptom(e.target.value)}
                            />
                            <Input
                                value={conclusion}
                                name="conclusion"
                                placeholder="K???t lu???n..."
                                onChange={(e) => setConclusion(e.target.value)}
                            />
                            <Button>S???a phi???u kh??m</Button>
                        </form>
                    </Offcanvas.Body>
                </Offcanvas>
            )}
        </div>
    );
}

export default Examination;
