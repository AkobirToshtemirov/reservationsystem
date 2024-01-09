package com.pdp.reservationsystem.service;

import com.pdp.reservationsystem.dto.CustomerDTO;
import com.pdp.reservationsystem.dto.FlightDTO;
import com.pdp.reservationsystem.entity.Booking;
import com.pdp.reservationsystem.entity.Customer;
import com.pdp.reservationsystem.entity.Flight;
import com.pdp.reservationsystem.exception.NotFoundException;
import com.pdp.reservationsystem.mapper.CustomerMapper;
import com.pdp.reservationsystem.mapper.FlightMapper;
import com.pdp.reservationsystem.repository.BookingRepository;
import com.pdp.reservationsystem.repository.CustomerRepository;
import com.pdp.reservationsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final BookingRepository bookingRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, FlightRepository flightRepository, FlightMapper flightMapper, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.bookingRepository = bookingRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.map(customerMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepository.save(customer);
        System.out.println("Added customer: " + customer);
    }

    public void deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
            System.out.println("Customer deleted with ID: " + id);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    public List<FlightDTO> getFlightsByCity(Long cityId) {
        List<Flight> flights = flightRepository.findByDepartureAirport_City_Id(cityId);
        return flights.stream()
                .map(flightMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void bookFlight(Long flightId, Long customerId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("Flight not found with ID: " + flightId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setFlight(flight);
        booking.setBookingTime(LocalDateTime.now());

        bookingRepository.save(booking);
    }

    public void cancelBookedFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("Flight not found with ID: " + flightId));

        Booking booking = bookingRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException("Booking not found for flight with ID: " + flightId));

        bookingRepository.delete(booking);
    }
}
