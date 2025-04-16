import { api } from "../utils/apiFunction";

export async function getRoomsAsync() {
    try {
        const response = await api.get("/rooms");
        return response.data;
    } catch (error) {
        console.error("Error fetching rooms:", error);
        throw error;
    }
}

export async function createRoomAsync(photo, roomType, roomPrice) {
    try {
        const formData = new FormData();
        formData.append("photo", photo);
        formData.append("roomType", roomType);
        formData.append("roomPrice", roomPrice);

        const response = await api.post("/rooms", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
        });

        if (response.status !== 201) {
            throw new Error("Failed to create room");
        }

        return response.data;
    } catch (error) {
        console.error("Error creating room:", error);
        throw error;
    }
}

export async function getRoomTypesAsync() {
    try {
        const response = await api.get("/rooms/types");

        if (response.status !== 200) {
            throw new Error("Failed to fetch room type");
        }

        return response.data;
    } catch (error) {
        console.log("Error fetching room type:", error);
        throw error;
    }
}