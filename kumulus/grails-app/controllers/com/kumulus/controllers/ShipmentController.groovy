package com.kumulus.controllers

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.kumulus.domain.*

@Transactional(readOnly = true)
class ShipmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Shipment.list(params), model:[shipmentInstanceCount: Shipment.count()]
    }

    def show(Shipment shipmentInstance) {
        respond shipmentInstance
    }

    def create() {
        respond new Shipment(params)
    }

    @Transactional
    def save(Shipment shipmentInstance) {
        if (shipmentInstance == null) {
            notFound()
            return
        }

        if (shipmentInstance.hasErrors()) {
            respond shipmentInstance.errors, view:'create'
            return
        }

        shipmentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shipmentInstance.label', default: 'Shipment'), shipmentInstance.id])
                redirect shipmentInstance
            }
            '*' { respond shipmentInstance, [status: CREATED] }
        }
    }

    def edit(Shipment shipmentInstance) {
        respond shipmentInstance
    }

    @Transactional
    def update(Shipment shipmentInstance) {
        if (shipmentInstance == null) {
            notFound()
            return
        }

        if (shipmentInstance.hasErrors()) {
            respond shipmentInstance.errors, view:'edit'
            return
        }

        shipmentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Shipment.label', default: 'Shipment'), shipmentInstance.id])
                redirect shipmentInstance
            }
            '*'{ respond shipmentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Shipment shipmentInstance) {

        if (shipmentInstance == null) {
            notFound()
            return
        }

        shipmentInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Shipment.label', default: 'Shipment'), shipmentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shipmentInstance.label', default: 'Shipment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
