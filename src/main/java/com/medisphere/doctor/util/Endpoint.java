package com.medisphere.doctor.util;

public class Endpoint {
    public static final String GET_ALL_DOCTORS_FOR_PATIENT = "/getAllDoctors";
    public static final String GET_DOCTOR_BY_ID = "/getDoctorById/{id}";
    public static final String UPDATE_DOCTOR_DETAILS = "/updateDoctorDetails/{id}";
    public static final String CREATE_DOCTOR = "/createDoctor";
    public static final String APPOINTMENT_STATUS_CHANGE = "/appointments/appointmentStatusChange";
    public static final String DELETE_DOCTOR = "/deleteDoctor/{id}";
    public static final String APPOINTMENT_GET_BY_DOCTOR_ID = "/appointments/allAppointmentsByDoctorId/{doctorId}";
    public static final String GET_ALL_UPLOADED_REPORTS = "/getMedicalReportsByDoctorId/{doctorId}";
    public static final String TELEMEDICINE_START_SESSION = "/telemedicine/sessions/{sessionId}/start";
    public static final String TELEMEDICINE_END_SESSION = "/telemedicine/sessions/{sessionId}/end";
    public static final String TELEMEDICINE_CREATE_PRESCRIPTION = "/telemedicine/prescriptions";
    public static final String TELEMEDICINE_UPDATE_PRESCRIPTION = "/telemedicine/prescriptions/{prescriptionId}";
    public static final String TELEMEDICINE_CREATE_SESSION = "/telemedicine/sessions/create";
}
