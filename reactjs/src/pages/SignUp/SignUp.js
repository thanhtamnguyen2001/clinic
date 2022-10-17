import Button from '~/components/Button';
import Form from '~/components/Form';
import Input from '~/components/Input';
import Select from '~/components/FormSelect';
import request from '~/utils/httpRequest';
import { useState } from 'react';

function SignUp() {
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [comfirmPassword, setConfirmPassword] = useState();
    const [phone, setPhone] = useState();
    const [file, setFile] = useState();

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('file', file);
        formData.append('username', username);
        formData.append('password', password);
        formData.append('comfirmPassword', comfirmPassword);
        formData.append('phone', phone);

        request
            .post('/auth/signup', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                },
            })
            .then(function (response) {});
    };
    return (
        <Form onSubmit={handleSubmit}>
            {/* <Input name="lastName" placeholder="Enter last name..." />
            <Input name="firstName" placeholder="Enter first name..." /> */}
            <Input name="phone" placeholder="Enter phone..." change={(e) => setPhone(e.target.value)} />
            <Input name="username" placeholder="Enter username..." change={(e) => setUsername(e.target.value)} />
            {/* <Select
                name="sex"
                options={[
                    { value: 0, optionName: '---sex---' },
                    { value: 'Nam', optionName: 'Nam' },
                    { value: 'Nữ', optionName: 'Nữ' },
                ]}
            /> */}
            <Input name="file" placeholder="Choose avatar..." type="file" change={(e) => setFile(e.target.files[0])} />
            <Input
                type="password"
                name="password"
                placeholder="Enter password..."
                change={(e) => setPassword(e.target.value)}
            />
            <Input
                type="password"
                name="comfirmPassword"
                placeholder="Enter confirmPassword..."
                change={(e) => setConfirmPassword(e.target.value)}
            />
            <Button type="submit" login>
                Register
            </Button>
        </Form>
    );
}

export default SignUp;
