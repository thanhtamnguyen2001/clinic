import classNames from 'classnames/bind';
import Header from '../components/Header';
import Footer from '../components/Footer';

import styles from './DefaultLayout.module.scss';
import './../../App';

import '~/App';

const cx = classNames.bind(styles);

function DefaultLayout({ children }) {
    return (
        <div className={cx('wrapper')}>
            <Header />
            <div className={cx('container')}>
                <div className={cx('content')}>{children}</div>
            </div>
            <Footer />
        </div>
    );
}

export default DefaultLayout;
