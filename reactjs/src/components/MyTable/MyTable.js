import classNames from 'classnames/bind';

import styles from './MyTable.module.scss';

const cx = classNames.bind(styles);

function Table({ small = false, title, headings = [], children, green = false }) {
    return (
        <div className={cx('container', { small, green })}>
            <div className={cx('row')}>
                <div className={cx('col-md-6 text-center mb-5')}>
                    <h2 className={cx('heading-section')}>{title}</h2>
                </div>
            </div>

            <div className={cx('row')}>
                <div className={cx('col-md-12')}>
                    <div className={cx('table-wrap')}>
                        <table className={cx('table')}>
                            <thead>
                                <tr>
                                    {headings.map((h, index) => (
                                        <th key={index}>{h}</th>
                                    ))}
                                </tr>
                            </thead>
                            <tbody>{children}</tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Table;
