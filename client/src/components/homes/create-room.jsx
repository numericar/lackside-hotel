import { useState } from "react";
import { createRoomAsync } from "../services/roomService";

export default function CreateRoom() {
    const [room, setRoom] = useState({
        photo: null,
        roomType: "",
        roomPrice: 0,
    });
    const [photoPreview, setPhotoPreview] = useState("");
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    function handleRoomInputChange(e) {
        const name = e.target.name;
        let value = e.target.value;

        if (name === "roomPrice") {
            if (!isNaN(value)) {
                setRoom({ ...room, roomPrice: parseFloat(value).toFixed(2) });
            } else {
                setRoom({ ...room, roomPrice: 0 });
            }
        }

        setRoom({ ...room, [name]: value });
    }

    function handlePhotoInputChange(e) {
        const selectedPhoto = e.target.files[0];
        setRoom({ ...room, photo: selectedPhoto });
        setPhotoPreview(URL.createObjectURL(selectedPhoto));
    }

    async function handleSubmit(e) {
        e.preventDefault();

        try {
            const response = await createRoomAsync(room.photo, room.roomType, room.roomPrice);

            if (response !== undefined) {
                setSuccessMessage("Create room was added.");
                setRoom({
                    photo: null,
                    roomType: "",
                    roomPrice: 0,
                });
                setErrorMessage("");
                setPhotoPreview("");
            }
        } catch (error) {
            setErrorMessage(error.message);
        }
    }

    return (
        <>
            {/*
                ใช้งาน flex flex-col และ element ลูกเป็น flex-1 เพื่อส่งต่อความสูล กรณ๊ใช้ min-h-auto ซึ่งไม่ได้ระบความสูงเริ่มต้น
                ทำให้ h-full ไม่ทำงาน
            */}
            <section className="container mx-auto min-h-screen flex flex-col p-10">
                <div className="flex justify-center items-center flex-1">
                    <div className=" flex flex-col gap-6 shadow-2xl p-10 rounded-2xl">
                        <h2 className="text-2xl">Create room</h2>
                        <div>
                            <label htmlFor="roomType" className="block mb-2">room type</label>
                            <input 
                                type="text" 
                                name="roomType" 
                                id="roomType" 
                                onChange={handleRoomInputChange}
                                className="border border-gray-500 px-4 py-1 rounded-lg w-full outline-none" />
                        </div>
                        <div>
                            <label htmlFor="roomPrice" className="block mb-2">price</label>
                            <input 
                                type="number" 
                                name="roomPrice" 
                                id="roomPrice" 
                                onChange={handleRoomInputChange}
                                className="border border-gray-500 px-4 py-1 rounded-lg w-full outline-none" />
                        </div>
                        <div>
                            <label htmlFor="photo" className="block mb-2">update photo</label>
                            <input 
                                type="file" 
                                name="photo" 
                                id="photo" 
                                onChange={handlePhotoInputChange}
                                className="border border-gray-500 px-4 py-1 rounded-lg w-full" />
                        </div>
                        {(photoPreview !== "") && (
                            <div>
                                <p className="mb-2">preview image</p>
                                <img src={photoPreview} className="w-100 rounded-2xl" />
                            </div>
                        )}
                        {(successMessage !== "") && (
                            <p className="text-green-500 font-bold">{successMessage}</p>
                        )}
                        {(successMessage !== "") && (
                            <p className="text-red-500 font-bold">{errorMessage}</p>
                        )}
                        <div className="mt-10">
                            <button className="bg-amber-500 w-full py-2 rounded-lg font-bold text-white cursor-pointer hover:bg-amber-400 transition" onClick={handleSubmit}>SUBMIT</button>
                        </div>
                    </div>
                </div>
            </section>
        </>
    );
}
