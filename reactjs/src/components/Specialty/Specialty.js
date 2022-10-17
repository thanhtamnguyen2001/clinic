import classNames from 'classnames/bind';
import { useEffect, useState } from 'react';
import commonService from '~/services/common.service';

import styles from './Specialty.module.scss';

const cx = classNames.bind(styles);

function Specialty() {
    const [specialties, setSpecialties] = useState([]);

    useEffect(() => {
        commonService.getSpecialties().then((res) => setSpecialties(res.data));
    }, []);

    return (
        <div className={cx('container')}>
            <h1 className={cx('title')}>Chuyên khoa tại Clinika</h1>
            <p className={cx('content')}>
                Chúng tôi phục vụ hết lòng vì người bệnh, mang lại hiệu quả tốt nhất trong quá trình điều trị đó là mục
                tiêu của chúng tôi
            </p>
            <nav>
                <ul className={cx('specialties')}>
                    {specialties.map((s) => (
                        <li key={s.id} className={cx('specialty')}>
                            <img className={cx('icon')} src={s.image} />
                            <h5 className={cx('name')}>{s.name}</h5>
                        </li>
                    ))}
                </ul>
            </nav>
        </div>
    );
}

export default Specialty;
