// import Button from 'react-bootstrap/Button';

import classNames from 'classnames/bind';
// import Button from '~/components/Button';
import config from '~/config';
import styles from './Header.module.scss';
import { FaArrowUp, FaArrowDown, FaSearch } from 'react-icons/fa';
import { useCallback, useEffect, useState } from 'react';
import Banner from '~/components/Banner';
import { Button, Container, Form, Nav, Navbar, NavDropdown } from 'react-bootstrap';
import { Link, NavLink, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from '~/actions/auth';
import { clearMessage } from '~/actions/message';

const cx = classNames.bind(styles);

const Header = () => {
    const [isActiveMenu, setIsActiveMenu] = useState(false);
    const [showPatientBoard, setShowPatientBoard] = useState(false);
    const [showDoctorBoard, setShowDoctorBoard] = useState(false);
    const [showNurseBoard, setShowNurseBoard] = useState(false);

    const { user: currentUser } = useSelector((state) => state.auth);
    const dispatch = useDispatch();

    let location = useLocation();

    useEffect(() => {
        if (['/sign-in', '/register'].includes(location.pathname)) {
            dispatch(clearMessage()); // clear message when changing location
        }
    }, [dispatch, location]);

    const logOut = useCallback(() => {
        dispatch(logout());
    }, [dispatch]);

    useEffect(() => {
        if (currentUser) {
            setShowPatientBoard(currentUser.roles.includes('ROLE_PATIENT'));
            setShowDoctorBoard(currentUser.roles.includes('ROLE_DOCTOR'));
            setShowNurseBoard(currentUser.roles.includes('ROLE_NURSE'));
        } else {
            setShowPatientBoard(false);
            setShowDoctorBoard(false);
            setShowNurseBoard(false);
        }
    }, [currentUser]);

    return (
        <div id={cx('containerHeader')} className={`${isActiveMenu ? cx('activeMenu') : ''}`}>
            <div className={cx('nav')}>
                <div className={cx('menu')}>
                    <Navbar className={cx('nav-bar')} collapseOnSelect expand="lg" variant="dark">
                        <Container>
                            <Navbar.Brand>
                                <Link style={{ display: 'inline-block' }} to={config.routes.home}>
                                    <img srcSet="logo.png" alt="" style={{ width: '45px' }} />
                                </Link>
                            </Navbar.Brand>
                            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                            <Navbar.Collapse id="responsive-navbar-nav">
                                <Nav className={'me-auto ' + cx('menu-nav')}>
                                    <NavLink to={config.routes.home} onClick={() => setIsActiveMenu(!isActiveMenu)}>
                                        Trang chủ
                                    </NavLink>
                                    {showDoctorBoard && (
                                        <>
                                            <NavLink
                                                to={config.routes.boardDoctor}
                                                onClick={() => setIsActiveMenu(!isActiveMenu)}
                                            >
                                                Doctor
                                            </NavLink>
                                            <NavLink
                                                to={config.routes.listRegisters}
                                                onClick={() => setIsActiveMenu(!isActiveMenu)}
                                            >
                                                Danh sách khám
                                            </NavLink>
                                        </>
                                    )}
                                    {showPatientBoard && (
                                        <NavLink
                                            onClick={() => setIsActiveMenu(!isActiveMenu)}
                                            to={config.routes.history}
                                        >
                                            Lịch sử khám
                                        </NavLink>
                                    )}
                                    {showNurseBoard && (
                                        <NavLink
                                            onClick={() => setIsActiveMenu(!isActiveMenu)}
                                            to={config.routes.appointmentHistory}
                                        >
                                            Lịch sử đăng ký
                                        </NavLink>
                                    )}
                                    {currentUser && (
                                        <>
                                            <NavLink
                                                onClick={() => setIsActiveMenu(!isActiveMenu)}
                                                to={config.routes.profile}
                                                relative="path"
                                            >
                                                {currentUser.username}
                                                <img src={currentUser.avatar} className="avatar" />
                                            </NavLink>
                                        </>
                                    )}

                                    <NavLink
                                        onClick={() => setIsActiveMenu(!isActiveMenu)}
                                        to={config.routes.regiter}
                                        rounded
                                        black
                                    >
                                        Appointment
                                    </NavLink>
                                    <NavDropdown title="Dịch vụ">
                                        <NavDropdown.Item href="#action/3.1">1</NavDropdown.Item>
                                        <NavDropdown.Item href="#action/3.2">2</NavDropdown.Item>
                                        <NavDropdown.Item href="#action/3.3">3</NavDropdown.Item>
                                        <NavDropdown.Divider />
                                        <NavDropdown.Item href="#action/3.4">4</NavDropdown.Item>
                                    </NavDropdown>
                                </Nav>
                                <Form className="d-flex">
                                    <Form.Control
                                        type="search"
                                        placeholder="Bạn cần tìm gì?"
                                        className="me-2"
                                        aria-label="Search"
                                    />
                                    <Button variant="outline-success" className={cx('menu-search')}>
                                        <FaSearch />
                                    </Button>
                                </Form>
                            </Navbar.Collapse>
                        </Container>
                        <div className={cx('container-login')}>
                            {!currentUser ? (
                                <>
                                    <NavLink onClick={() => setIsActiveMenu(!isActiveMenu)} to={config.routes.signIn}>
                                        <Button variant="outline-danger" onClick={() => setIsActiveMenu(!isActiveMenu)}>
                                            Đăng nhập
                                        </Button>
                                    </NavLink>
                                    <NavLink onClick={() => setIsActiveMenu(!isActiveMenu)} to={config.routes.signUp}>
                                        <Button
                                            variant="outline-success"
                                            onClick={() => setIsActiveMenu(!isActiveMenu)}
                                        >
                                            Đăng ký
                                        </Button>
                                    </NavLink>
                                </>
                            ) : (
                                <NavLink
                                    onClick={() => {
                                        setIsActiveMenu(!isActiveMenu);
                                        logOut();
                                    }}
                                    to={config.routes.signIn}
                                >
                                    <Button variant="outline-danger">Sign out</Button>
                                </NavLink>
                            )}
                        </div>
                    </Navbar>
                    <div id={cx('direction')} onClick={() => setIsActiveMenu(!isActiveMenu)}>
                        <FaArrowUp className={cx('iconShowMenu')}></FaArrowUp>
                        <FaArrowDown className={cx('iconHideMenu')}></FaArrowDown>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Header;
