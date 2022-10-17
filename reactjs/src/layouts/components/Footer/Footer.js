import classNames from 'classnames/bind';

import styles from './Footer.module.scss';

const cx = classNames.bind(styles);

function Footer() {
    return (
        <footer>
            <div className={cx('wrapper')}>
                <div className={cx('footer-top')}>Footer top</div>
                <div className={cx('footer-bottom')}>
                    <img
                        className={cx('logo')}
                        alt="logo"
                        src="https://res.cloudinary.com/tamdev/image/upload/v1664423131/clinic/theme_clinika_logo_dark_szxwrg.png"
                    />

                    <p>&copy; Clinika Theme by ModelTheme.com. All rights Reserved.</p>
                </div>
            </div>
        </footer>
    );
}

export default Footer;
