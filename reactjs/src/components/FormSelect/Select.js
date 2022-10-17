import classNames from 'classnames/bind';
import styles from './Select.module.scss';

const cx = classNames.bind(styles);

function Select({ lable, name, options = [{ value: '', optionName: '' }] }) {
    return (
        <div className={cx('wrap-select')}>
            <select className={cx('select')} name={name}>
                {options.map((option, index) => (
                    <option key={index} value={option.value}>
                        {option.optionName}
                    </option>
                ))}
            </select>
        </div>
    );
}

export default Select;
