package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(FacturaAlmacenController)
@Mock(FacturaAlmacen)
class FacturaAlmacenControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/facturaAlmacen/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.facturaAlmacenInstanceList.size() == 0
        assert model.facturaAlmacenInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.facturaAlmacenInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.facturaAlmacenInstance != null
        assert view == '/facturaAlmacen/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/facturaAlmacen/show/1'
        assert controller.flash.message != null
        assert FacturaAlmacen.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaAlmacen/list'


        def facturaAlmacen = new FacturaAlmacen()

        // TODO: populate domain properties

        assert facturaAlmacen.save() != null

        params.id = facturaAlmacen.id

        def model = controller.show()

        assert model.facturaAlmacenInstance == facturaAlmacen
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaAlmacen/list'


        def facturaAlmacen = new FacturaAlmacen()

        // TODO: populate valid domain properties

        assert facturaAlmacen.save() != null

        params.id = facturaAlmacen.id

        def model = controller.edit()

        assert model.facturaAlmacenInstance == facturaAlmacen
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaAlmacen/list'

        response.reset()


        def facturaAlmacen = new FacturaAlmacen()

        // TODO: populate valid domain properties

        assert facturaAlmacen.save() != null

        // test invalid parameters in update
        params.id = facturaAlmacen.id

        controller.update()

        assert view == "/facturaAlmacen/edit"
        assert model.facturaAlmacenInstance != null

        facturaAlmacen.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/facturaAlmacen/show/$facturaAlmacen.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/facturaAlmacen/list'

        response.reset()

        def facturaAlmacen = new FacturaAlmacen()

        // TODO: populate valid domain properties
        assert facturaAlmacen.save() != null
        assert FacturaAlmacen.count() == 1

        params.id = facturaAlmacen.id

        controller.delete()

        assert FacturaAlmacen.count() == 0
        assert FacturaAlmacen.get(facturaAlmacen.id) == null
        assert response.redirectedUrl == '/facturaAlmacen/list'


    }


}