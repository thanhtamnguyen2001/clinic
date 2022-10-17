import classNames from 'classnames/bind';

import styles from './Search.module.scss';

const cx = classNames.bind(styles);

function Search({ children }) {
    return (
        <div>
            <div className={cx('search')}>
                <input placeholder="Type here to filter even further..." />
            </div>
        </div>
    );
}

export default Search;
