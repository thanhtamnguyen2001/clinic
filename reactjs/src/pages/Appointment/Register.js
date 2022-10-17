import request from '~/utils/httpRequest';
import classNames from 'classnames/bind';

import styles from './Register.module.scss';
import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Form from '~/components/Form';
import Input from '~/components/Input';
import Button from '~/components/Button';
import FormTitle from '~/components/FormTitle';

import registerService from '~/services/register.service';

const cx = classNames.bind(styles);

function Register() {
    let navigate = useNavigate();

    const nameRef = useRef();

    const handleSubmit = (e) => {
        e.preventDefault();
        const registerData = Object.fromEntries(new FormData(e.target).entries());

        registerService.createRegister(registerData).then((register) => {
            if (register.status === 201) {
                return navigate('/');
            }
        });
    };
    return (
        <div className={cx('wrapper')}>
            <Form onSubmit={handleSubmit}>
                <FormTitle>Make a appointment</FormTitle>
                <Input name="name" placeholder="Enter name" />
                <Input name="phone" placeholder="Enter phone" />
                <Input name="healthIssues" placeholder="Enter issues" />
                <Input name="examinationTime" type="date" />
                <Button login type="submit">
                    Appointment
                </Button>
            </Form>
        </div>
    );
}

export default Register;
