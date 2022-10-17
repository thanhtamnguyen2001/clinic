import { useEffect, useState } from 'react';
import Table from 'react-bootstrap/Table';
import request from '~/utils/httpRequest';

import { Navigate, useNavigate } from 'react-router-dom';

import RegisterService from '~/services/register.service';

import classNames from 'classnames/bind';

import styles from './ListRegisters.module.scss';
import Button from '~/components/Button';
import config from '~/config';
import Examination from '../Examination';
import MyTable from '~/components/MyTable';

const cx = classNames.bind(styles);

function ListRegisters() {
    const [registers, setRegisters] = useState([]);
    const [registerId, setRegisterId] = useState();
    const navigate = useNavigate();

    useEffect(() => {
        RegisterService.getListRegisters().then(
            (res) => {
                setRegisters(res.data);
            },
            (error) => {
                const message =
                    (error.response && error.response.data && error.response.data.message) ||
                    error.message ||
                    error.toString();

                setRegisters(message);
            },
        );
    }, []);

    const handleExamination = (register) => {
        return navigate(config.routes.examination, { state: { register } });
    };

    return (
        <div className={cx('wrapper')}>
            <MyTable title={'Danh sách khám bệnh'} headings={['Tên bệnh nhân', 'Triệu chứng bệnh']}>
                {registers.map((register, index) => (
                    <tr key={index}>
                        <td>{register.name}</td>
                        <td>{register.healthIssues}</td>
                        <td>
                            <Button
                                onClick={() => {
                                    handleExamination(register);
                                }}
                                small
                                green
                            >
                                Khám bệnh
                            </Button>
                        </td>
                    </tr>
                ))}
            </MyTable>
        </div>
    );
}

export default ListRegisters;
