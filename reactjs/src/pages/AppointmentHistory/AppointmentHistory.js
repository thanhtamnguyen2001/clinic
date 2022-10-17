import { useEffect, useState } from 'react';
import { Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import registerService from '~/services/register.service';
import convertTimestamp from '~/utils/convertTimestamp';

function AppointmentHistory() {
    const { user: currentUser } = useSelector((state) => state.auth);
    const [registers, setRegisters] = useState([]);

    useEffect(() => {
        registerService.getRegistersByCurrentUser().then(
            (res) => setRegisters(res.data),
            (error) => setRegisters(error.response.data),
        );
    }, []);

    if (!currentUser) {
        return <h1>Chưa đăng nhập</h1>;
    }

    return (
        <div>
            {registers.length && (
                <Table striped bordered hove="true">
                    <thead>
                        <tr>
                            <th>Mã đăng ký</th>
                            <th>Tên bệnh nhân</th>
                            <th>Số điện thoại</th>
                            <th>Ngày đăng ký</th>
                            <th>Ngày hẹn</th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        {registers.map((r) => (
                            <tr>
                                <td>{r.id}</td>
                                <td>{r.name}</td>
                                <td>{r.phone}</td>
                                <td>{convertTimestamp(r.createdDate)}</td>
                                <td>{convertTimestamp(r.examinationTime)}</td>
                                <td>{r.verified ? <span>Đã xác nhận</span> : <span>Chưa xác nhận</span>}</td>
                            </tr>
                        ))}
                    </tbody>
                </Table>
            )}
        </div>
    );
}

export default AppointmentHistory;
