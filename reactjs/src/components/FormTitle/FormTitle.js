import classNames from 'classnames/bind';
import styles from './FormTitle.module.scss';

const cx = classNames.bind(styles);

function FormTitle({ children, ...passProps }) {
    return <span className={cx('title')}>{children}</span>;
}

export default FormTitle;
