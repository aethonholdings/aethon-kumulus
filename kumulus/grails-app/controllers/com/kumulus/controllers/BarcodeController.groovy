package com.kumulus.controllers

import com.kumulus.domain.*
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BarcodeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def logisticsService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Barcode.list(params), model:[barcodeInstanceCount: Barcode.count()]
    }

    def show(Barcode barcodeInstance) {
        respond barcodeInstance
    }

    def create() {
        respond new Barcode(params)
    }

    @Transactional
    def save(Barcode barcodeInstance) {
        if (barcodeInstance == null) {
            notFound()
            return
        }

        if (barcodeInstance.hasErrors()) {
            respond barcodeInstance.errors, view:'create'
            return
        }

        barcodeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'barcodeInstance.label', default: 'Barcode'), barcodeInstance.id])
                redirect barcodeInstance
            }
            '*' { respond barcodeInstance, [status: CREATED] }
        }
    }

    def edit(Barcode barcodeInstance) {
        respond barcodeInstance
    }

    @Transactional
    def update(Barcode barcodeInstance) {
        if (barcodeInstance == null) {
            notFound()
            return
        }

        if (barcodeInstance.hasErrors()) {
            respond barcodeInstance.errors, view:'edit'
            return
        }

        barcodeInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Barcode.label', default: 'Barcode'), barcodeInstance.id])
                redirect barcodeInstance
            }
            '*'{ respond barcodeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Barcode barcodeInstance) {

        if (barcodeInstance == null) {
            notFound()
            return
        }

        barcodeInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Barcode.label', default: 'Barcode'), barcodeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'barcodeInstance.label', default: 'Barcode'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    
    def generate() {
        
    }
}
